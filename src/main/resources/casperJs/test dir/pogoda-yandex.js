
var casper = require('casper').create({
	pageSettings: {
        userAgent: "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
		}
});
casper.start('https://pogoda.yandex.ru/');
var weatherSelector="div.current-weather__thermometer";
var commentSelector="span.current-weather__comment";
var weather;
var comment;
var error=false;
var output={
	result:"",
	error:""
}

casper.waitForSelector(	weatherSelector,
	function then(){
		weather=this.getHTML(weatherSelector);
	},
	function onTimeout() {
		error=true;
	},
	2000
);

casper.waitForSelector(	commentSelector,
	function then(){
		comment=this.fetchText(commentSelector);
	},
	function onTimeout() {
		error=true;
	},
	2000
);
casper.then( function() {
	output.error=error;
	output.result=weather+" "+comment;
});



casper.then(function(){
	this.echo(JSON.stringify(output));
});

casper.run();