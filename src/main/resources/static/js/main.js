
const loadModalFields = async function(coinName) {
  let response = await fetch('../coins/info/' + coinName);

  let myJson = await response.json();
  var total = (Math.round((myJson.price * myJson.amount) * 100) / 100).toFixed(2);
  var percentage = (myJson.percentage).toFixed(2);
  console.log(myJson.percentage);
  document.getElementById("name").innerHTML = myJson.name;
  document.getElementById("total").innerHTML = total + " $";
  document.getElementById("amount").innerHTML = myJson.amount + " " + myJson.token;
  document.getElementById("percentage").innerHTML = percentage + " %";
  document.getElementById("image").src = "../images/" + myJson.token + ".png";

  document.getElementById("trading-view").innerHTML = "";
  var script = document.createElement("script");
  script.setAttribute('src', 'https://s3.tradingview.com/external-embedding/embed-widget-mini-symbol-overview.js');
  script.text = '{' +
    '"symbol": "BITFINEX:' + myJson.token + 'USD",' +
    '"width": 350,' +
    '"height": 280,' +
    '"locale": "es",' +
    '"dateRange": "3m",' +
    '"colorTheme": "light",' +
    '"trendLineColor": "#37a6ef",' +
    '"underLineColor": "#E3F2FD",' +
    '"isTransparent": false,' +
    '"autosize": false,' +
    '"largeChartUrl": ""' +
    '}';
  document.getElementById("trading-view").appendChild(script);
}

function open(x) {
  $(x).modal('show');
}


