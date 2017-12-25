$(document).ready(function() {

        // chart.js

        var barChartData = {
            labels: ["10", "20", "30", "40", "50", "60+"],
            datasets: [
            {
                label: '?',
                backgroundColor: "rgba(129,6,35,1)",
                data: [65, 40, 80, 19, 56, 55],
            }, 
            {
                // hidden: true,
                label: '?',
                // borderWidth: 0;
                backgroundColor: "rgba(67,70,190,1)",
                data: [35, 60, 20, 81, 44, 45],
            }]

        };

        // window.onload = function() {
            var ctx = document.getElementById("cavas-chart").getContext("2d");
            window.myBar = new Chart(ctx, {
                type: 'bar',
                data: barChartData,
                options: {
                    elements: {
                        rectangle: {
                            borderWidth: 0
                        }
                    },
                    responsive: true,
                    legend: {
                        position: 'bottom',
                        borderWidth: 0
                    },
                    borderWidth: 0,
                    title: {
                        display: false
                    }
                }
            });
      });