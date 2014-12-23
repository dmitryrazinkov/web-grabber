var casper = require('casper').create({
	pageSettings: {
        userAgent: "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36"
		}
});
casper.start('http://google.com/');

casper.thenEvaluate(function(term) {
     document.querySelector('input[name="q"]').setAttribute('value', term);
     document.querySelector('form[name="gbqf"]').submit();
                    },  casper.cli.get(0));

casper.then(function() {
    path=this.getElementAttribute("h3.r a","href");
	this.thenOpen(path);
});


casper.then( function() {
    var now=new Date().getTime();
    var name=now+".jpg";
    this.capture(name, undefined, {
        format: 'jpg',
        quality: 100
    })
});


casper.run();