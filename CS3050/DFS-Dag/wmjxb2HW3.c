#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include"input_error.h"


enum color_{
	WHITE,
	GRAY,
	BLACK
};

typedef struct edge__{
	int value;
	struct edge__ *next;
} Edge;

typedef struct node__{
	int value;
	Edge *edgeList;
	int color;
	int start;
	int finish;
} Node;


void addEdge(int vertex, int edge, Node root[]){
	/*
 	*Function can be used to build a graph/add edges
	*to vertices for both transpose and regular graphs
	*by just switching vertex/edge around
	*
	*Builds a list representation for graph
	*/


	/*Ensure both vertex&edge vertices are in graph*/
	root[vertex-1].value=vertex;

	if(root[edge-1].value!=edge) root[edge-1].value=edge;

	/*Add edge to adj. list of vertex*/
	Edge *temp=root[vertex-1].edgeList;
        if(root[vertex-1].edgeList==NULL){
                root[vertex-1].edgeList=malloc(sizeof(Edge));
                root[vertex-1].edgeList->value=edge;
                return;
        }

        while(temp->next!=NULL) temp=temp->next;
        temp->next=malloc(sizeof(Edge));
        (temp->next)->value=edge;
} 


void parse_file(FILE *fPtr, Node *graph, int numVertex){
/*
 * Parse through file, handling any errors that may occur
 * Add edges to graph one by one
 */

	char *value;	
	int val_size=0;

	char *line=NULL;
	int lineLen=0,vertex=0, edge=0;
	size_t nbytes=0;

	while((lineLen=getline(&line, &nbytes, fPtr))!=-1){
		line[lineLen-1]='\0';
		if(line[0]!='(') exit(PARSING_ERROR_INVALID_FORMAT);
		int i=1;
		while(1){  //Get vertex value
			if(isdigit(line[i])!=0){
				value=realloc(value, sizeof(char)*(++val_size));
				if(value==NULL) exit(PARSING_ERROR_EMPTY_FILE);
				value[val_size-1]=line[i];
			}
			else if(line[i]==','){
				value[val_size]='\0';
				vertex=atoi(value);
				free(value);
				value=NULL;
				val_size=0;
				i++;
				break;
			}
			else exit(PARSING_ERROR_INVALID_FORMAT);
			i++;
		}

		while(1){  //Get edge value
                        if(isdigit(line[i])!=0){
                                value=realloc(value, sizeof(char)*(++val_size));
                                if(value==NULL) exit(PARSING_ERROR_EMPTY_FILE);
                                value[val_size-1]=line[i];
                        }
                        else if(line[i]==')'){
                                value[val_size]='\0';
                                edge=atoi(value);
                                free(value);
                                value=NULL;
                                val_size=0;
                                i++;
                                break;
                        }
                        else exit(PARSING_ERROR_INVALID_FORMAT);
                        i++;
                }

		if(line[i]!='\0' && line[i]!='\n') exit(PARSING_ERROR_INVALID_FORMAT);
		if(vertex<1 || vertex>numVertex || edge<1 || edge>numVertex){
			exit(INTEGER_IS_NOT_A_VERTEX);
		}


		addEdge(vertex, edge, graph);
	}
	free(line);
}

void freeEdge(Edge *edge){
        if(edge==NULL) return;
        freeEdge(edge->next);
        free(edge);
}


void freeGraph(Node *graph, int  numVertex){
        int i;
        for(i=0; i<numVertex; i++){
                freeEdge(graph[i].edgeList);
        }
        free(graph);
}


void visit(Node *graph, int vertex, int *time, Edge *list){
/*
 * Visit each node when called, if vertex is already gray,
 * it has already been found, and it has a cycle
 */
	*time=*time+1;
	graph[vertex-1].color=GRAY; //Visited
	graph[vertex-1].start=*time;

	/*Visit all edges in adjacency list*/
	Edge *edge=graph[vertex-1].edgeList;
	while(edge!=NULL){
		int currEdge=(edge->value)-1;
		if(graph[currEdge].color==WHITE){
			visit(graph, graph[currEdge].value,time, list);
		}
		else if(graph[currEdge].color==GRAY){
		/*Catch if graph is not a dag*/
			*time=-1;
			freeEdge(list);
			list=NULL;
		}
		if(*time==-1) break;
		edge=edge->next;
	}

	if(*time==-1) return;
	graph[vertex-1].color=BLACK;
	*time=*time+1;
	graph[vertex-1].finish=*time;

	/*Add edge to list*/
	if(list->value==0) list->value=graph[vertex-1].value;
	else{
		while((list->next)!=NULL) list=list->next;
		list->next=malloc(sizeof(Edge));
		list->next->value=graph[vertex-1].value;
	}
}

void depthFirstSearch(Node *graph, int numVertex, Edge *list){
/*
 * Perform depth first search of graph
 */
	int i;
	int time=0;
	for(i=0; i<numVertex; i++){
		if(graph[i].edgeList!=NULL) graph[i].color=WHITE;
		else graph[i].value=i+1;
	}

	for(i=0; i<numVertex; i++){
		if(time==-1) break;
		if(graph[i].color==WHITE){
			visit(graph,graph[i].value, &time, list);
		}
		i++;
	}
}

void printEdges(Edge *list, FILE *outfPtr){
	/*
 * Function prints out linked list sorted by finishing
 * time
 * */

	if(list==NULL) return;
	printEdges(list->next, outfPtr);
	fprintf(outfPtr,"%d\n", list->value);
}

int main(int argc, char *argv[]){
	if(argc!=3){
		exit(INCORRECT_NUMBER_OF_COMMAND_LINE_ARGUMENTS);
	}
	
	FILE *fPtr=fopen(argv[1],"r");
	if(fPtr==NULL) exit(FILE_FAILED_TO_OPEN);

	int numVertex=0;
	fscanf(fPtr, "%d\n", &numVertex);
	if(numVertex<=0) exit(PARSING_ERROR_INVALID_FORMAT);

	Node *graph=malloc(sizeof(Node)*numVertex);
	parse_file(fPtr, graph, numVertex);
	
	FILE *outfPtr=fopen(argv[2], "w");
        if(outfPtr==NULL) exit(FILE_FAILED_TO_OPEN);
	Edge *list=malloc(sizeof(Edge));

	depthFirstSearch(graph, numVertex, list);
	
	printEdges(list, outfPtr);
	
	freeGraph(graph, numVertex);
	freeEdge(list);
	fclose(fPtr);
	fclose(outfPtr);
	return 0;
}
