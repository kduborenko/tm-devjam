var renderPoints = function() {
}

TicketPathApp
    .component('mapComponent', {
        template:
            '<div id="map-container"></div>',
        controller: function() {
         var dark = [
                {
                    "featureType": "all",
                    "elementType": "labels.text.fill",
                    "stylers": [
                        {
                            "saturation": 36
                        },
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 40
                        }
                    ]
                },
                {
                    "featureType": "all",
                    "elementType": "labels.text.stroke",
                    "stylers": [
                        {
                            "visibility": "on"
                        },
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 16
                        }
                    ]
                },
                {
                    "featureType": "all",
                    "elementType": "labels.icon",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "geometry.fill",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 20
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "geometry.stroke",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 17
                        },
                        {
                            "weight": 1.2
                        }
                    ]
                },
                {
                    "featureType": "landscape",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 20
                        }
                    ]
                },
                {
                    "featureType": "poi",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 21
                        }
                    ]
                },
                {
                    "featureType": "road.highway",
                    "elementType": "geometry.fill",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 17
                        }
                    ]
                },
                {
                    "featureType": "road.highway",
                    "elementType": "geometry.stroke",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 29
                        },
                        {
                            "weight": 0.2
                        }
                    ]
                },
                {
                    "featureType": "road.arterial",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 18
                        }
                    ]
                },
                {
                    "featureType": "road.local",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 16
                        }
                    ]
                },
                {
                    "featureType": "transit",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 19
                        }
                    ]
                },
                {
                    "featureType": "water",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#000000"
                        },
                        {
                            "lightness": 17
                        }
                    ]
                }
            ]

            var self = this;

            var mapOptions = {
              zoom: 3,
              center: new google.maps.LatLng(41.850033, -87.6500523),
              mapTypeId: google.maps.MapTypeId.TERRAIN,
              styles: dark
            }

            this.map = new google.maps.Map(document.getElementById('map-container'), mapOptions);

            var goldStar = {
                path: 'M 0 0 a 15 15 0 1 0 0.00005 0',
                fillColor: '#e01d35',
                fillOpacity: 0.8,
                scale: 1,
                strokeColor: 'red',
                strokeWeight: 1
              };

//              var marker = new google.maps.Marker({
//                position: self.map.getCenter(),
//                icon: goldStar,
//                map: self.map
//              });

              renderPoints = function(data) {
                for (var i=0; i<data.length; i++) {
                    var e = data[i];
                    if (e.venues && e.venues[0]) {
                        var location = e.venues[0].location;
                    new google.maps.Marker({
                                                   position: new google.maps.LatLng(location.latitude, location.longitude),
                                                   icon: goldStar,
                                                   map: self.map
                                               });
                    }

                }
                //alert(data);
              }

//            this.markers = [];
//
//            var infoWindow = new google.maps.InfoWindow();
//
//            var createMarker = function (info){
//
//              var marker = new google.maps.Marker({
//                  map: self.map,
//                  position: new google.maps.LatLng(info.lat, info.long),
//                  title: info.city
//              });
//              marker.content = '<div class="infoWindowContent">' + info.desc + '</div>';
//
//              google.maps.event.addListener(marker, 'click', function(){
//                  infoWindow.setContent('<h2>' + marker.title + '</h2>' + marker.content);
//                  infoWindow.open($scope.map, marker);
//              });
//
//              $scope.markers.push(marker);
//
//            }
//
//            for (i = 0; i < cities.length; i++){
//              createMarker(cities[i]);
//            }
//
//            this.openInfoWindow = function(e, selectedMarker){
//              e.preventDefault();
//              google.maps.event.trigger(selectedMarker, 'click');
//            }
        }
    });