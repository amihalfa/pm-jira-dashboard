$(function () {
    $.ajax({
        url :'/ajax/points',
        dataType : 'json'
    }).done(function(data){
        $('#container').highcharts({
            title: {
                text: 'Burn Down'
            },
            tooltip : {
                enabled:false
            },
            credits: {
                enabled:false
            },
            exporting: {
                enabled:false
            },
            legend: {
                enabled:false
            },
            xAxis: {
                categories: data.availableDays
            },
            yAxis: {
                title: ''
            },
            series: [
                {
                    name: 'Restant',
                    data: data.remainingPoints,
                    color: '#990033'
                },
                {
                    name: 'Cible',
                    data: [[0, data.maximum], [data.availableDays.length-1, 0]],
                    color: '#ababab'
                }
            ]
        });
    });

});