async function addToCart(userid, bookid) {
    try {
        const response = await fetch('/carts/' + userid + '/items/' + bookid, {
            method: 'post',
        });
        console.log('Completed!', response);
    } catch(err) {
        console.error(`Error: ${err}`);
    }
}