var casper = require('casper').create({
	pageSettings: {
        userAgent: "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
		}
});
casper.start('http://vk.com/');


casper.then(function() {
	this.fill('form[name="login"]', {
		'email': this.cli.get('email'),
		'pass': this.cli.get('pass')
	}, true);
}
);

page=casper.cli.get('page');
casper.thenOpen(page);

var selector="div.wall_text";
var error=false;
var output={
	result:"",
	error:""
};

casper.waitForSelector(	selector,
	function then(){
		output.result=this.fetchText(selector).replace(/\s+/g,' ').trim();
	},
	function onTimeout() {
		error=true;
	},
	2000
);

casper.then(function() {
    output.error=error;
	this.echo(JSON.stringify(output));
}
);
casper.run();
