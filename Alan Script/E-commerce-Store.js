onCreateUser(p => {
    p.userData.itemList = {en:"fresh banana_|red apple_|ginger|rice|diet coke|coca cola can_|sprite can_|apple and grape juice|orange juice|pepsi can_"};
})

intent("What is this app", p=> {
    p.play("This is an online e-commerce app where you can purchase any groceries online")
});

intent("(Show|Open) me my (favourite|favorite) items", p=> {
    p.play({command: "showFavorite"});
    p.play("Showing you your favorite items");
});

intent("(Show|Open) my cart items", p=> {
    p.play({command: "showCart"});
    p.play("Showing items present in your cart")
})

intent("(Go|) (back to|) home", p=> {
    p.play({command: "showHome"});
    p.play("Navigating back to Home")
})

intent("(Go|Show|Go to) my profile", p=> {
    p.play({command: "showProfile"});
    p.play("Showing you your profile")
})

intent(`(Add|I want|want) $(ITEM u:itemList) to favorite`, p=> {
    p.play({command: "addFavorite", item: `${p.ITEM.value}`});
    p.play(`${p.ITEM.value} is added to your favorites`)
})

intent(`(Add|I want|want) $(NUMBER) (kg|gram|grams|) $(ITEM u:itemList) (to cart|)`, p => {
     var a = `${p.NUMBER.value}`;
     if(p.ITEM.value == "ginger" || p.ITEM.value == "rice") {
         if(p.NUMBER.value >= 250 && p.NUMBER.value%250 == 0) {
             a = `${p.NUMBER.value} gram`;
             p.NUMBER.value = p.NUMBER.value/250;
         }
         else if(p.NUMBER.value >= 0 && p.NUMBER.value <= 100) {
             a = `${p.NUMBER.value} kg`;
             p.NUMBER.value *= 4;
         }
     }
    if(p.ITEM.value == "red apple" || p.ITEM.value == "fresh banana"){
        a = `${p.NUMBER.value} kg`;
    }
     p.play({command: "addItem", item: `${p.ITEM.value}`, count: `${p.NUMBER.value}`});
     p.play(`Done. Added ${a} ${p.ITEM.value}`);
});
