TicketPathApp
    .component('mapComponent', {
        template:
            '<div id="map-container"></div>',
        controller: function() {
            var self = this;

            var mapOptions = {
              zoom: 4,
              center: new google.maps.LatLng(41.850033, -87.6500523),
              mapTypeId: google.maps.MapTypeId.TERRAIN
            }

            this.map = new google.maps.Map(document.getElementById('map-container'), mapOptions);

            var goldStar = {
                path: 'M 0 0 a 50 50 0 1 0 0.00001 0',
                fillColor: 'red',
                fillOpacity: 0.8,
                scale: 1,
                strokeColor: 'gold',
                strokeWeight: 14
              };

              var marker = new google.maps.Marker({
                position: self.map.getCenter(),
                icon: goldStar,
                map: self.map
              });

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