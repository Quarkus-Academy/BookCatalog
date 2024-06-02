function onLoad() {
  loadStreams();
}


/**
 * Load stream with EventSource, get MessageEvent<Book> onmessage
 */
function loadStreams() {
  const bookStream = new EventSource('/stream/book');
  bookStream.onmessage = function (/** @type { MessageEvent<Book> } */event) {
    /** @type { Book } */
    const book = JSON.parse(event.data);
    console.log('Received new Book from Event stream', book);
  };
}
