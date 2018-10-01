#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<string.h>
#include"input_error.h"

/*
 * William Johnke
 * ID:14253530
 * 11/10/2016
 * CS3050
 * Kruskal Algorithm
 */


enum color_{
	WHITE,
	GRAY,
	BLACK
};

typedef struct edge__{
	int value;
	int weight;
	struct edge__ *next;
} Edge;

typedef struct queue__{
	int vertOne;
	int vertTwo;
	int weight;
	struct queue__ *next;
} Queue;

typedef struct node__{
	int value;
	Edge *edgeList;
	int color;
	int start;
	int finish;
	struct node__ *parent;
	int rank;
} Node;


Node *find(Node *base){
	/*
 	*Function finds node and returns parent node
	*Uses path compression as it runs
	*/
	if (base->parent!=base){
		base->parent=find(base->parent);
	}

	return base->parent;
}

void unionMerge(Node *x, Node *y){
	Node *xRoot=find(x);
	Node *yRoot=find(y);
	if(xRoot==yRoot) return;
	
	//Else merge smaller tree and bigger tree by rank
	if((xRoot->rank)>(yRoot->rank)){
		yRoot->parent=xRoot;
	}
	else if((xRoot->rank)<(yRoot->rank)){
		xRoot->parent=yRoot;
	}
	else{ //same rank
		xRoot->parent=yRoot;
		yRoot->rank=yRoot->rank+1;
	}


}


void addEdge(int vertex, int edge, int weight, Node root[]){
	/*
	*Builds a list representation for graph w/ edge weights
	*/

	/*Ensure both vertex&edge vertices are in graph*/
	root[vertex-1].value=vertex;
	if(root[edge-1].value!=edge) root[edge-1].value=edge;

	/*Add edge to adj. list of both vertices*/
	Edge *temp=root[vertex-1].edgeList;
	
	//Initialize parent to self
	root[vertex-1].parent=&root[vertex-1];
	root[edge-1].parent=&root[edge-1];

	//Initialize rank to 0
	root[vertex-1].rank=0;
	root[edge-1].rank=0;

        if(root[vertex-1].edgeList==NULL){
                root[vertex-1].edgeList=malloc(sizeof(Edge));
                root[vertex-1].edgeList->value=edge;
		root[vertex-1].edgeList->weight=weight;
        
		if(root[edge-1].edgeList==NULL){
                	root[edge-1].edgeList=malloc(sizeof(Edge));
                	root[edge-1].edgeList->value=vertex;
			root[edge-1].edgeList->weight=weight;
		}
		return;
        }
	if(root[edge-1].edgeList==NULL){
        	root[edge-1].edgeList=malloc(sizeof(Edge));
       		root[edge-1].edgeList->value=vertex;
          	root[edge-1].edgeList->weight=weight;
		return;
	}
	

        while(temp->next!=NULL) temp=temp->next;
        temp->next=malloc(sizeof(Edge));
        (temp->next)->value=edge;
	(temp->next)->weight=weight;

	temp=root[edge-1].edgeList;
	while(temp->next!=NULL) temp=temp->next;
        temp->next=malloc(sizeof(Edge));
        (temp->next)->value=vertex;
        (temp->next)->weight=weight;
} 

Queue *buildQueue(Queue *queue, int vertex, int edge, int weight){

	if(queue==NULL){
		queue=malloc(sizeof(Queue));
                queue->vertOne=vertex;
                queue->vertTwo=edge;
                queue->weight=weight;
                queue->next=NULL;
		return queue;
	}
	if(weight<(queue->weight)){
		Queue *temp=queue;
		queue=malloc(sizeof(Queue));
		queue->weight=weight;
		queue->vertOne=vertex;
		queue->vertTwo=edge;
		queue->next=temp;
		return queue;
    	}
        queue->next=buildQueue(queue->next, vertex, edge, weight);
	return queue;
}



void parse_file(FILE *fPtr, Node *graph, int numVertex,Queue *queue){
/*
 * Parse through file, handling any errors that may occur
 * Add edges to graph one by one
 */

	char *value;	
	int val_size=0;

	char *line=NULL;
	int lineLen=0,vertex=0, edge=0, weight=0;
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
                        else if(line[i]==','){
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
		while(1){  //get weight Value
			if(isdigit(line[i])!=0){
				value=realloc(value, sizeof(char)*(++val_size));
				if(value==NULL) exit(PARSING_ERROR_INVALID_FORMAT);
				value[val_size-1]=line[i];
			}
			else if(line[i]==')'){
				value[val_size]='\0';
				weight=atoi(value);
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
		addEdge(vertex, edge, weight, graph);
		queue=buildQueue(queue, vertex, edge, weight);
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

void printMST(Edge *sPtr, FILE *outFP){
	while(sPtr!=NULL){
		fprintf(outFP, "(%d,%d)\n", sPtr->value, sPtr->weight);
		Edge *temp=sPtr;
		sPtr=sPtr->next;
		free(temp);
		temp=NULL;
	}
}


void kruskalTree(Node *graph, Queue *queue, FILE *outFP){
/*
 * Algorithm iterates through queue, which holds
 * edges sorted by weight, accesses index of edge
 * Checks through the find() function if the vertex
 * and edge are of the same set. find() will also
 * utilize path compression to set parents of each nodes equal
 * to the root-most parent, for easier searching
 * in future. If vertices form a cycle/are of same set
 * ignore, otherwise add to minimum spanning tree and print
*/
	Queue *prev=queue;
	queue=queue->next;
	free(prev);
	Node *x, *y;
	Edge *mst, *sPtr;
	int i=0;
	while(queue!=NULL){
		x=&(graph[queue->vertOne-1]);
		y=&(graph[queue->vertTwo-1]);
		if(find(x)!=find(y)){
			unionMerge(x, y);
			if(i==0){
				mst=malloc(sizeof(Edge));
				mst->value=x->value;
				mst->weight=y->value;
				sPtr=mst;
				mst->next=NULL;
			}
			else{
				mst->next=malloc(sizeof(Edge));
				mst=mst->next;
				mst->value=x->value;
				mst->weight=y->value;
				mst->next=NULL;
			}
			i++;
		}

		prev=queue;
		queue=queue->next;
		free(prev);	
	}
	printMST(sPtr, outFP);
}


void visit(Node *graph, int vertex, int *time){
/*
 *  * Visit each node when called, if vertex is already gray,
 *   * it has already been found, and it has a cycle
 *    */
        *time=*time+1;
        graph[vertex-1].color=GRAY; //Visited
        graph[vertex-1].start=*time;
	Edge *edge=graph[vertex-1].edgeList;
        while(edge!=NULL){
                int currEdge=(edge->value)-1;
                if(graph[currEdge].color==WHITE){
                        visit(graph, graph[currEdge].value,time);
                }
                else if(graph[currEdge].color==BLACK){
                /*Catch if graph is not a dag*/
                        *time=-1;
                }
                if(*time==-1) break;
                edge=edge->next;
        }

        if(*time==-1) return;
        graph[vertex-1].color=BLACK;
        *time=*time+1;
        graph[vertex-1].finish=*time;
}

int depthFirstSearch(Node *graph, int numVertex){
/*
 *  * Perform depth first search of graph
 *   */
        int i;
        int time=0;
        for(i=0; i<numVertex; i++){
                if(graph[i].edgeList!=NULL) graph[i].color=WHITE;
                else graph[i].value=i+1;
        }

        for(i=0; i<numVertex; i++){
                if(time==-1) break;
                if(graph[i].color==WHITE){
                        visit(graph,graph[i].value, &time);
                }
                i++;
        }

	for(i=0; i<numVertex; i++){
		if(graph[i].color==WHITE){
			return 0;
		}
	}
	return 1;
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
	//Using simple linked list to sort queue instead of min-priority queue,
	//if edges are > |v|^2, then it will be more efficient.
	Queue *queue=malloc(sizeof(Queue));
	parse_file(fPtr, graph, numVertex, queue);
	
	FILE *outfPtr=fopen(argv[2], "w");
        if(outfPtr==NULL) exit(FILE_FAILED_TO_OPEN);

	if(depthFirstSearch(graph, numVertex)==1){
		//Use Kruskal's algorithm to find MST, and output
		kruskalTree(graph, queue, outfPtr);
	}
	else{
		fprintf(outfPtr, "0");
	}
	freeGraph(graph, numVertex);
	fclose(fPtr);
	fclose(outfPtr);
	return 0;
}
