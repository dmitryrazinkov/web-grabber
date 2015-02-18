var casper = require('casper').create({
	pageSettings: {
        userAgent: "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
		}
});
casper.start('http://moex.com/ru/markets/currency/');

casper.then(function(){
	if(this.exists('button[type="button"]')){
		this.click('button[type="button"]');
		};
});

var EURselector='tr[data-title="EURRUB_TOM"]>td[data-name="LAST"]';
var EUR;
var error=false;
var output={
	result:"",
	error:""
}

casper.waitForSelector(	EURselector,
	function then(){
		EUR=this.fetchText(EURselector);
	},
	function onTimeout() {
		error=true;
	},
	2000
)

casper.then( function() {
	output.error=error;
	if (EUR.replace(',','.')<this.cli.get('lessThan')) {
	    output.result="true";
	} else {
	    output.result="false";
	}
})

casper.then(function(){
	this.echo(JSON.stringify(output));
});


casper.run();