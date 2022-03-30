# Super-Mario
SCU 《面向对象程序设计导论》课程作业。
队员：李佳员，徐浩杰。

## 项目内容
《超级马里奥》原作为日本任天堂公司出版的2D 冒险系列游戏，玩家需要操控一个头戴红色帽子身穿蓝色背带裤的中年大叔。

- 人物可以移动通过AD键进行控制，可以跳跃，通过W控制，跳的时候可以跳到管子和砖块上面。

- 人物可以撞普通的砖块或者带有包含物的砖块使得砖块可以向上稍微移动，砖块上的一些包含物也会随着砖块移动。

- 怪物分为三种，分别为：板栗仔、乌龟和食人花。
- 马里奥可以通过跳跃的方式踩死怪物：
  - 板栗仔在被踩的时候会变扁；
  - 乌龟被踩的时候走动状态会变成龟壳状态，龟壳状态被碰到可以变成跑动的龟壳状态，跑动的龟壳可以杀死马里奥，运动的龟壳在运动的时候被马里奥踩到会变成静止的龟壳；
  - 食人花长在管道中，会定时出现对管道上方的物体进行攻击，当马里奥踩在管道上的时候不会出现。

- 还有三种道具是包含在砖块中的：分别是金币、蘑菇和星星。马里奥自下向上顶砖块之后砖块上方会出现随机道具。其中金币停留在原地，而星星和蘑菇会向右方向移动。马里奥可以通过触碰的方式收集道具，不同道具有不同的加成效果。
  - 收集金币会获得更多分数。
  - 收集蘑菇之后体型会变大。
  - 收集星星之后会变成无敌状态。

- 任何物品，尤其是可移动物，在碰到洞之后会掉落到洞中，人物掉落之后会损失一命，人物一共有五条生命。每次正面碰到乌龟或者板栗仔，或者掉落到洞中之后便会损失一条生命，每次损失生命则该关卡从头开始。当五条生命全部损失之后便会到Game Over状态。

- 当马里奥走到地图的最后一个模型之后的位置的时候说明本关通过，本关通过时会有马里奥跳下拉动旗帜，旗帜拉倒底端的时候会向右跑到城堡位置。跑到城堡位置即属于本关卡已经通过，游戏结束。

- 游戏界面上方会有剩余生命与计分系统。当玩家杀死怪物，或者收集某种道具都会获取相应的分数加成。分数显示在面板上方。 

## 制作计划
制作模式采取迭代式开发，按照类的编写——类的集成——项目检测的循环逐步完成项目。

- 四月第一周完成人物与生命值系统。
- 四月第二周完成普通砖块。
- 四月第三周集成测试。
- 四月第四周完成怪物，计分系统。
- 五月第一周完成特殊砖块和道具。
- 五月第二周集成测试。
