
var casper = require('casper').create({
	pageSettings: {
        userAgent: "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
		}
});
casper.start('https://pogoda.yandex.ru/');


casper.then(function(){
	this.echo(this.getHTML("div.current-weather__thermometer"));
	this.echo(this.getHTML("span.current-weather__comment"));
})
//casper.then( function(){ this.capture('ya.jpg', undefined, {
//        format: 'jpg',
//        quality: 100
//    })
//});

casper.run();