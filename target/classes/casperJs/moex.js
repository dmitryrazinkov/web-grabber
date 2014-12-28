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

casper.then(function(){
	this.echo(this.fetchText('tr[data-title="USDRUB_TOM"]>td[data-name="LAST"]'));
	this.echo(this.fetchText('tr[data-title="USDRUB_TOM"]>td[data-name="CHANGE"]'));
});

//casper.then( function() {
//    this.capture('moex.jpg', undefined, {
//        format: 'jpg',
//        quality: 100
//    })
//});

casper.run();