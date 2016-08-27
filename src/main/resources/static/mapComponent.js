TicketPathApp.component('mapComponent', {
                 template: '<div>{{  $ctrl.test }}</div>',
                 controller: function() {
                     this.test = 'hello world'
                 }
             });