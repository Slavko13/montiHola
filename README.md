# montiHola
!!!Важно!!!
Скрипт для создание базы данных

CREATE TABLE public.statistic
(
    id integer NOT NULL,
    game_count integer NOT NULL,
    loses integer NOT NULL,
    win_rate double precision NOT NULL,
    wins integer NOT NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.statistic
    OWNER to postgres;
    
INSERT INTO public.statistic(
	id, game_count, loses, win_rate, wins)
	VALUES (1, 0, 0, 0, 0);
  
!!!Важно!!!

Попробуй обыграть меня
<h1> Игра в парадокс Монти Хола <h1>

Представьте, что вы стали участником игры, в которой вам нужно выбрать одну из трёх дверей. За одной из дверей находится автомобиль, за двумя другими дверями — козы. 
Вы выбираете одну из дверей, например, номер 1, после этого ведущий, который знает, где находится автомобиль, а где — козы, открывает одну из оставшихся дверей, например, номер 3, за которой находится коза. 
После этого он спрашивает вас — не желаете ли вы изменить свой выбор и выбрать дверь номер 2? Увеличатся ли ваши шансы выиграть автомобиль, если вы примете предложение ведущего и измените свой выбор?


<h3>Используемые технологии:</h3>

<h2>Backend main:</h2>
<ul>
   <li>Java 8</li>
   <li>Spring framework (spring boot)</li>
   <li>Postgresql</li>
</ul

<h2>Frontend:</h2>
<ul>
   <li>Freemarker</li>
   <li>Vue js</li>
   <li>CSS</li>
   <li>Thymeleaf</li>
   <li>Bootstrap</li>
</ul>


<h3>Использование</h3>

На вашем сервере базы данных, создать новую БД по скрипту выше Далее перейти на localhost:8080/game, если не произойдет авторедирект вдруг.
На странице /game уже можно будет играть в игру(все описания и подсказки + статистика находятся на этой странице)
