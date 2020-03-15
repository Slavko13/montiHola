var gameProcessApi = Vue.resource('/game/process');
var statisticApi = Vue.resource('/getStat');
var autoPlayApi = Vue.resource('/autoPlay');
var clearStatsApi = Vue.resource('/clearStats');


Vue.component('game', {

    props: ['stat' , 'frontendStats' ],

    data: function() {
        return {
            userChoice: "",
            gameCount: frontendStats.gameCount,
            correctDoor: Math.floor(Math.random() * (3 - 1 + 1)) + 1,
            gameMessage: "Ну давай, выбирай",
            wins: frontendStats.wins,
            loses: frontendStats.loses,
            winRateAfterChange: frontendStats.winRateAfterChange,
            winRateWithOutChange: frontendStats.winRateWithOutChange
        }
    },

    //этот параметр для того чтобы обновить данные в html
    watch: {
        valuteAttr: function (newVal, oldVal) {
            this.stat = newVal;

        },
        messageAttr: function (newVal, oldVal) {
            this.gameMessage = newVal;
        },
        winsAttr: function (newVal, oldVal) {
            this.wins = newVal;
        },
        losesAttr: function (newVal, oldVal) {
            this.loses = newVal;
        },
        winRateAfterChangeAttr: function (newVal, oldVal) {
            this.winRateAfterChange = newVal;
        },
        winRateWithOutChangeAttr: function (newVal, oldVal) {
            this.winRateWithOutChange = newVal;
        },
        gameCountAttr: function (newVal, oldVal) {
            this.gameCount = newVal;
        }
    },

    template:
        '<div>' +
            '<div>' +
                '<button class="btn btn-info door" dat-id="1"  name="1" data-toggle="modal" data-target="#myModal" @click="choose(1,correctDoor)" >Первая дверь</button>' +
                '<button class="btn btn-success door" data-id="2"   name="2" data-toggle="modal" data-target="#myModal" @click="choose(2,correctDoor)">Вторая дверь</button>' +
                '<button class="btn btn-primary door" data-id="3"   name="3" data-toggle="modal" data-target="#myModal" @click="choose(3, correctDoor)">Третья дверь</button>' +
            '</div>' +
            '<div>Всего игры: {{gameCount}}</div>'+
            '<div>Поражений: {{loses}}</div>'+
            '<div>Побед: {{wins}}</div>'+
            '<br/>' +
            '<div>Побед со сменой выбора: {{stat[0].winsAfterChange}}</div>'+
            '<div>Поражений со сменой выбора: {{stat[0].losesAfterChange}}</div>'+
            '<div>Игр со сменой выбора: {{stat[0].gameCountAfterChange}}</div>'+
            '<div>Процент побед со сменой выбора: {{winRateAfterChange}}%</div>'+
            '<br/>'+
            '<div>Побед без смены выбора: {{stat[0].winsWithOutChange}}</div>'+
            '<div>Поражений без смены выбора: {{stat[0].losesWithOutChange}}</div>'+
            '<div>Игр без смены выбора: {{stat[0].gameCountWithOutChange}}</div>'+
            '<div>Процент побед без смены выбора: {{winRateWithOutChange}}%</div>'+
            '<h3>{{gameMessage}} </h3>'+
            '<button class="btn btn-danger" @click="autoPlay" style="margin-right: 10px">Автоплей</button>'+
            '<button class="btn btn-warning" @click="clearStats">Очистить статистку</button>'+
        '</div>',


    methods: {
        choose: function(id, correctDoor) {

            //Тут идет сбор информации по нажатой кнопки и где реально спрятан приз
            var gameResponse = {correctDoor: correctDoor, userChoice: id};
            //Post запрос на проверку этапа игры
                gameProcessApi.save({},gameResponse).then(
                result => {
                    result.json().then(
                        data => {
                            this.gameMessage = data.message;
                            console.log(result);
                            if (data.userChoice !== 0) {
                                element = document.getElementsByName(data.freeDoor.toString());
                                element[0].disabled = true;
                            }


                        }
                    );
                },
                    //Post запрос на обновление статистики и так же тут потом новый приз будет спрятан
                setTimeout(() => { statisticApi.save({}, {}).then(result => result.json().then(
                    data => {

                        var gameCountNow = data.gameCountAfterChange + data.gameCountWithOutChange;
                        console.log(this.gameCount + "lala1");
                        console.log(gameCountNow + "lala1");
                        if(gameCountNow !== this.gameCount) {
                            //обновляем стратистику
                            console.log("lala")
                            this.gameCount = gameCountNow;
                            this.stat.splice(0, 1, data);
                            this.wins = data.winsAfterChange + data.winsWithOutChange;
                            this.loses = data.losesAfterChange + data.losesWithOutChange;
                            if(data.gameCountAfterChange === 0) {
                                this.winRateAfterChange = 0;
                            }
                            else {
                                this.winRateAfterChange = data.winsAfterChange / data.gameCountAfterChange * 100;
                            }
                            if(data.gameCountWithOutChange === 0) {
                                this.winRateWithOutChange = 0;
                            }
                            else {
                                this.winRateWithOutChange = data.winsWithOutChange / data.gameCountWithOutChange * 100;
                            }
                            this.correctDoor = Math.floor(Math.random() * (3 - 1)) + 1;
                            elemets =  document.getElementsByClassName("door");
                            elemets[0].disabled = false;
                            elemets[1].disabled = false;
                            elemets[2].disabled = false;
                        }


                    })) }, 200),

            );



        },
        autoPlay: function () {
            autoPlayApi.save({},{}).then(
                result => {
                    result.json().then (
                        data => {
                            this.gameCount = data.gameCountAfterChange + data.gameCountWithOutChange;
                            this.stat.splice(0, 1, data);
                            this.wins = data.winsAfterChange + data.winsWithOutChange;
                            this.loses = data.losesAfterChange + data.losesWithOutChange;
                            if(data.gameCountAfterChange === 0) {
                                this.winRateAfterChange = 0;
                            }
                            else {
                                this.winRateAfterChange = data.winsAfterChange / data.gameCountAfterChange * 100;
                            }
                            if(data.gameCountWithOutChange === 0) {
                                this.winRateWithOutChange = 0;
                            }
                            else {
                                this.winRateWithOutChange = data.winsWithOutChange / data.gameCountWithOutChange * 100;
                            }
                        }
                    )
                }
            )
        },
        clearStats: function () {
            clearStatsApi.save({},{}).then(
                result => {
                    result.json().then (
                        data => {
                            this.gameCount = data.gameCountAfterChange + data.gameCountWithOutChange;
                            this.stat.splice(0, 1, data);
                            this.wins = data.winsAfterChange + data.winsWithOutChange;
                            this.loses = data.losesAfterChange + data.losesWithOutChange;
                            if(data.gameCountAfterChange === 0) {
                                this.winRateAfterChange = 0;
                            }
                            else {
                                this.winRateAfterChange = data.winsAfterChange / data.gameCountAfterChange * 100;
                            }
                            if(data.gameCountWithOutChange === 0) {
                                this.winRateWithOutChange = 0;
                            }
                            else {
                                this.winRateWithOutChange = data.winsWithOutChange / data.gameCountWithOutChange * 100;
                            }
                        }
                    )
                }
            )
        }


    }
});


var app = new Vue({
    el: '#app',
    template:
        '<div>' +
        '<div class="container-fluid">' +
        '<game   :stat="stat" :frontendStats="frontendStats"/>'+
        '</div>'+
        '</div>',
    data: {
        stat: [frontendData.statistic],
        correctDoor: frontendData.door,
        frontendStats: frontendStats,
    }
});