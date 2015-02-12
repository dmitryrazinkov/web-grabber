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

var USDselector='tr[data-title="USDRUB_TOM"]>td[data-name="LAST"]';
var changeSelector='tr[data-title="USDRUB_TOM"]>td[data-name="CHANGE"]';
var USD;
var change;
var error=false;
var output={
	result:"",
	error:""
}

casper.waitForSelector(	USDselector,
	function then(){
		USD=this.fetchText(USDselector);
	},
	function onTimeout() {
		error=true;
	},
	2000
)

casper.waitForSelector(	changeSelector,
	function then(){
		change=this.fetchText(changeSelector);
	},
	function onTimeout() {
		error=true;
	},
	2000
)
casper.then( function() {
	output.error=error;
	output.result=USD+" "+change;
})

casper.then(function(){
	this.echo(JSON.stringify(output));
});


casper.run();