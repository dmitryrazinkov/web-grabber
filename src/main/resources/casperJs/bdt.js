var casper = require('casper').create({
	pageSettings: {
        userAgent: "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
		}
});
casper.start(casper.cli.get("page"));

casper.then(function(){
	this.click('div.j-htmlCode > div > div');
});

var priceSelector="div.price";
var output={
	result:"false",
	error:"false"
}

casper.then(function() {
    path=this.getElementAttribute("#bodyRdadrioPop > div > iframe","src");
	this.thenOpen(path);

});

casper.then(function(){
		this.waitForSelector(priceSelector,
			function then(){
				var text=this.fetchText(priceSelector).replace(/\s+/g,' ').trim();
				for(var i=0; i<text.length; i=i+12) {
					if (text.substr(i,11)!="Билетов нет") {
						output.result="true";
					}
				}
			},
			function onTimeout() {
				output.error="true";
			},
			2000
		);
});

casper.then(function(){
	this.echo(JSON.stringify(output));
});

casper.run();