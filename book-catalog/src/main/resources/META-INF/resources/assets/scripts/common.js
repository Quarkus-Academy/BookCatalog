function onLoad() {
  loadStreams();
  pingLibraryService();
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

async function pingLibraryService() {
  const response = await fetch("http://localhost:8081/api", {
    method: "GET"
  });
  if (response.status != 200) {
    console.error("Unable to connect");
    return;
  }

  const responseBody = await response.text();
  console.log("Pinged LibraryService", responseBody);
}
