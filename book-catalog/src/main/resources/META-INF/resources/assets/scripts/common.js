function onLoad() {
  loadStreams();
  pingLibraryService();
}


/**
 * Load stream with EventSource, get MessageEvent<Book> onmessage
 */
function loadStreams() {

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
