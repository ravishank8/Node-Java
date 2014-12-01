var async = require("async");
var request = require("request");
var fs = require('fs');
var redis = require("redis"),
client = redis.createClient();
var nodeUrlJp, nodeUrlUs, siteIdUs, siteIdJs;
var navbarJPRestUrl = "http://shop.readycommerce.jp:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories"
var cacheService


function getRedisConfig() {
	client.get("node-readycommerce-url-jp", function(err, reply) {
		nodeUrlJp = reply.toString();
	});
	client.get("node-readycommerce-url-us", function(err, reply) {
		nodeUrlUs = reply.toString();
	});
	client.get("site-id-us", function(err, reply) {
		siteIdUs = reply.toString();
	});
	client.get("site-id-jp", function(err, reply) {
		siteIdJs = reply.toString();
	});
	client.get("node-server-port", function(err, reply) {
		nodeServerPort = reply.toString();
	});




}


// inintiate async calls
exports.init = function (req, res, next) {
console.log("iniitating init")
  
getRedisConfig()
var restHostServer, countryRestUrl, navbarUSRestUrl, navbarJPRestUrl

        async.series({
            countryRestCall: function (callback) {

			client.get("rest-hostServer", function(err, reply) {
			  console.log("The key value = "+reply.toString());
			  restHostServer = reply.toString();

			countryRestUrl = ''+restHostServer+"/rest/model/com/sgs/browse/EndecaContentCollectionActor/getSupportedCountries";
			 
			  request({
                    url: countryRestUrl,
                    json: true
                }, function (error, response, body) {

                    if (!error && response.statusCode === 200) {
                        countryDropdown = body
                        callback(null, countryDropdown);

                    }
                })
			});

                

            },
            navbarUSRestCall: function (callback) {
            	client.get("rest-hostServerUs", function(err, reply) {
			    var  restHostServerUs = reply.toString();
			    navbarUSRestUrl = restHostServerUs+"/rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories";
				 	  request({
	                    url: navbarUSRestUrl,
	                    json: true
		                }, function (error, response, body) {

		                    if (!error && response.statusCode === 200) {
		                        navbarUS = ""
		                        callback(null, navbarUS);

		                    }
		                })
				});

 
            },
            navbarJPRestCall: function (callback) {
				client.get("rest-hostServerJp", function(err, reply) {
					var  restHostServerJp = reply.toString();
					navbarJPRestUrl = restHostServerJp+"/rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories";
				 	

          		      request({
	      	              url: navbarJPRestUrl,
	                  		  json: true
	                }, function (error, response, body) {

	                    if (!error && response.statusCode === 200) {
		                        navbarJP = body
		                        callback(null, navbarJP);

		                    }
		                })

                	})


	            }
	        }, function (err, results) {
	            console.log("Async Result" + results)
	            cacheService = results
	            
	        });

}

 
function userLocal(hostname) {
    var local, hostnameUs, hostnameJp; 
    
	hostnameUs = '"'+nodeUrlUs+":"+nodeServerPort+'"';
	hostnameJp = '"'+nodeUrlJp+":"+nodeServerPort+'"';
	 
    if (hostname == hostnameUs) {
        local = siteIdUs;
        console.log("returning" + local)
        return local;
    } else if (hostname == hostnameJp) {
        local = siteIdJs;
        return local;
    }
    return local;
}
 

function createActiveFlag(homePageObject, localObject ) {
	var flagJSON = homePageObject.countryDropdown
	for ( var i = 0; i < flagJSON.countries.length; i++) {
		console.log(JSON.stringify(flagJSON.countries[i].label))
		if(flagJSON.countries[i].label == localObject){
			homePageObject.countryDropdown['activeFlag'] = {"currentFlag":flagJSON.countries[i] }
			return flagJSON;
		}
	}

}


function bindHeaderLayout(domainName) {
    var localObject = userLocal(domainName);
	if(localObject == siteIdUs )
		jsonObject["Navbar"]= cacheService.navbarUSRestCall

	if(localObject == siteIdJs)
		jsonObject["Navbar"]= cacheService.navbarJPRestCall
    
    return local;
}

 



function readJSONFile(filename, callback) {
  fs.readFile(filename, function (err, data) {
    if(err) {
      callback(err);
      return;
    }
    try {
      callback(null, JSON.parse(data));
    } catch(exception) {
      callback(exception);
    }
  });
}
 


var homePageObject
exports.homepage = function (req, res, next) {
    var domainName = JSON.stringify(req.headers.host)
    var localObject = userLocal(domainName);
    var homeRestCall = "http://delvmpllbbab09:7003/rest/model/com/sgs/browse/EndecaContentCollectionActor/getHome"

    request({
        url: homeRestCall,
        json: true
    }, function (error, response, body) {
 			











        async.series({
        
             navbarUSRestCall1: function (callback) {

                client.get("rest-hostServerUs", function(err, reply) {
                var  restHostServerUs = reply.toString();
                navbarUSRestUrl = restHostServerUs+"/rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories";
                      request({
                        url: navbarUSRestUrl,
                        json: true
                        }, function (error, response, body) {

                            if (!error && response.statusCode === 200) {
                                navbarUS = body
                                 console.log("Respnse 1")
                                callback(null, navbarUS);

                            }
                        })
                });

                

            },
             navbarUSRestCall2: function (callback) {

                client.get("rest-hostServerUs", function(err, reply) {
                var  restHostServerUs = reply.toString();
                navbarUSRestUrl = restHostServerUs+"/rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories";
                      request({
                        url: navbarUSRestUrl,
                        json: true
                        }, function (error, response, body) {

                            if (!error && response.statusCode === 200) {
                                 console.log("Respnse 2")
                                navbarUS = body
                                callback(null, navbarUS);

                            }
                        })
                });

                

            },
             navbarUSRestCall3: function (callback) {

                client.get("rest-hostServerUs", function(err, reply) {
                var  restHostServerUs = reply.toString();
                navbarUSRestUrl = restHostServerUs+"/rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories";
                      request({
                        url: navbarUSRestUrl,
                        json: true
                        }, function (error, response, body) {

                            if (!error && response.statusCode === 200) {
                                 console.log("Respnse 3")
                                navbarUS = body
                                callback(null, navbarUS);

                            }
                        })
                });

                

            },
             navbarUSRestCall4: function (callback) {

                client.get("rest-hostServerUs", function(err, reply) {
                var  restHostServerUs = reply.toString();
                navbarUSRestUrl = restHostServerUs+"/rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories";
                      request({
                        url: navbarUSRestUrl,
                        json: true
                        }, function (error, response, body) {
                    console.log("Respnse 4")
                            if (!error && response.statusCode === 200) {
                                navbarUS = body
                                callback(null, navbarUS);

                            }
                        })
                });

                

            } 
         
            }, function (err, results) {
                console.log("Async Result" + results)
                cacheService = results


             request({
               url: "http://delvmpllbbab09:7003//rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategories",
               json: true
                }, function (error, response, body) {
                    homePageObject = {
                               
                
            };
                    
                    console.log("this is my response of NAV Service!!!!!!!")
                    console.log(body)
                    homePageObject["Navbar"]= body;
                    

                      if (error) return next(error);
                        res.render('index', {
                            title: 'Home page',
                            createHomeTemplate: homePageObject || []
                        }); 

                }) 
                
            });








 
    })
 
};
 

exports.ajxTemplateResponse = function (req, res, next) {
    var domainName = JSON.stringify(req.headers.host)
    var localObject = userLocal(domainName);
    var url1 = "http://delvmpllbbab10/store/Home?format=json"
    request({
        url: url1,
        json: true
    }, function (error, response, body) {

        if (!error && response.statusCode === 200) {
             homePageObject = {
                countryDropdown: cacheService.countryRestCall,                 
                Carousel: body.contents[0].mainContent[0].contents[0].mainCollection[0].MediaBanner,
                PromoBanner: body.contents[0].mainContent[0].contents[0].mainCollection[1].MediaBanner
            };

             if(localObject == "United States" )
             	homePageObject["Navbar"]= cacheService.navbarUSRestCall
             
             if(localObject == "Japan" )
             	homePageObject["Navbar"]= cacheService.navbarJPRestCall


			var compileJSON = 	createActiveFlag(homePageObject, localObject )
	      
            if (error) return next(error);
            res.render('index', {
                title: 'Home Page',
                createHomeTemplate: homePageObject || [],
                layout:false
            }, function(err, html){
			  var response = {
			    some_data: '',
			    some_more_data: [5, 8, 10, 67],
			    my_html: html
			  };
			  res.send(response);
			});
            

        }
    })

}


 
exports.productDetail = function (req, res, next) {
		var productId = req.params.id;
		console.log(productId)
		console.log("Product id :"+ productId) 
			 res.render('productDetail', {
                title: 'Home Page' 
               
            });


}


exports.category = function (req, res, next) {
	var categoryId = req.params.id;
	var domainName = JSON.stringify(req.headers.host)
    var localObject = userLocal(domainName);
    var clpRestCall = "http://delvmpllbbab09:7003/rest/model/com/sgs/browse/EndecaContentCollectionActor/getCategory?N="+categoryId
      request({
        url: clpRestCall,
        json: true
    }, function (error, response, body) {
    		var homePageObject =""
    	    homePageObject = {
                countryDropdown: cacheService.countryRestCall,     
                ProductCarousels: body.categoryLandingPageContent.ProductCarousels,
                HeroBanner: body.categoryLandingPageContent.HeroBanner.Media[0],
                CategoryChildList: body.categoryLandingPageContent.CategoryChildList[0],
                All_Products:  body.categoryLandingPageContent["AllProducts"]

            };
			if(localObject == "United States" )
				homePageObject["Navbar"]= cacheService.navbarUSRestCall

			if(localObject == "Japan" )
				homePageObject["Navbar"]= cacheService.navbarJPRestCall

			var compileJSON = 	createActiveFlag(homePageObject, localObject );
            res.render('category', {
                title: 'Home Page',
                createHomeTemplate: homePageObject || [] 
            });
        })
}