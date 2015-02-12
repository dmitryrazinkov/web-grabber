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

//casper.then(function(){
//this.echo(this.fetchText("#page_wall_posts_count"));
//}
//)

casper.then(function() {
	this.echo(this.fetchText("div.wall_text").replace(/\s+/g,' ').trim());
}
);


casper.run();