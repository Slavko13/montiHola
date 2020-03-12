var gameProcessApi = Vue.resource('/game/process');
var statisticApi = Vue.resource('/getStat');


Vue.component('game', {
    props: ['stat' ],

    data: function() {
        return {
            finalDoor: "",
            correctDoor: Math.floor(Math.random() * (3 - 1 + 1)) + 1,
            gameMessage: "Ну давай, выбирай",
        }
    },

    //этот параметр для того чтобы обновить данные в html
    watch: {
        valuteAttr: function (newVal, oldVal) {
            this.stat = newVal;

        },
        messageAttr: function (newVal, oldVal) {
            this.gameMessage = newVal;
        }
    },

    template:
        '<div>' +
            '<div>' +
                '<button class="btn btn-info" dat-id="1"  data-toggle="modal" data-target="#myModal" @click="choose(1,correctDoor)" >Первая дверь</button>' +
                '<button class="btn btn-success" data-id="2"  data-toggle="modal" data-target="#myModal" @click="choose(2,correctDoor)">Вторая дверь</button>' +
                '<button class="btn btn-primary" data-id="3"  data-toggle="modal" data-target="#myModal" @click="choose(3, correctDoor)">Третья дверь</button>' +
            '</div>' +
            '<div>Всего игры: {{stat[0].gameCount}}</div>'+
            '<div>Побед: {{stat[0].wins}}</div>'+
            '<div>Поражений: {{stat[0].loses}}</div>'+
            '<div>Процент побед: {{stat[0].winRate}}%</div>'+
            '<h3>{{gameMessage}} </h3>'+

        '</div>',


    methods: {
        choose: function(id, correctDoor) {

            //Тут идет сбор информации по нажатой кнопки и где реально спрятан приз
            var gameResponse = {correctDoor: correctDoor, finalDoor: id};
            //Post запрос на проверку этапа игры
                gameProcessApi.save({},gameResponse).then(
                result => {
                    this.gameMessage = result.bodyText;
                },
                    //Post запрос на обновление статистики и так же тут потом новый приз будет спрятан
                setTimeout(() => { statisticApi.save({}, {}).then(result => result.json().then(
                    data => {
                        console.log(data.gameCount);
                        console.log(this.stat[0].gameCount);

                        if(data.gameCount !== this.stat[0].gameCount) {
                            //обновляем стратистику
                            this.stat.splice(0, 1, data);
                            this.correctDoor = Math.floor(Math.random() * (3 - 1)) + 1;
                        }


                    })) }, 200),

            );



        }


    }
});


var app = new Vue({
    el: '#app',
    template:
        '<div>' +
        '<div class="container-fluid">' +
        '<game   :stat="stat"/>'+
        '</div>'+
        '</div>',
    data: {
        winRate: frontendData.winRate,
        stat: [frontendData.statistic],
        correctDoor: frontendData.door
    }
});ё