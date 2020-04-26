function openPage(path) {
window.open(path,'MyWindow','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=no,resizable=no,width=900,height=600,left=540,top=500');

}

function open(x){
    $(x).modal('show');
}

var chart = new cryptowatch.Embed('bitfinex', 'btcusd', {
  timePeriod: '1d',
  width: 650,
  presetColorScheme: 'standar'
});
chart.mount('#chart-container');
