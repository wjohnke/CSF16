/*
    We navigated to http://www.w3schools.com/ and opened our console in devtools
    Typing jQuery resulted in an error:
        jQuery
        Uncaught ReferenceError: jQuery is not defined(...)
    
    
    It looks like w3schools does not use jQuery on their homepage.
    That's ok, we can include it for them:
        jq = document.createElement('script')
        jq.src = 'http://code.jquery.com/jquery-2.1.4.min.js'
        document.body.appendChild(jq)
    
    
    Now we have jQuery :)
        jQuery
        function (a,b){return new n.fn.init(a,b)}
    
    
    The next step is to copy and paste this into the console:
    
*/
(function( $ ){

    // Bieberize
    $.fn.bieberize = function() {  
        var counter = 0;
        return this.each(function(index, elem) {
            setTimeout(function() {
                $(elem).append('<img src="https://ec2-54-226-50-144.compute-1.amazonaws.com/myPrepClassLectures/12.6.16%20Closures%20and%20jQuery/5.%20plugins/images/bieber.jpg">');
            }, counter);
            counter += 50;
        });
    };

})( jQuery );

/*
    ...and call it:
        $('*').bieberize()
*/