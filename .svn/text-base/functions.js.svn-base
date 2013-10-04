var points1Array = points1_roadSet1Formatted;
var points2Array = points2_roadSet1Formatted;
var points3Array = points3_roadSet1Formatted;

function changeFile(sel) {
	ext = sel.options[sel.selectedIndex].value;
	switch (ext) {
	case "ny":
		if (typeof points1_ny === 'undefined') {
			alert("no array called points1_ny in points1.js");
		} else {
			points1Array = points1_ny;
			points2Array = points2_ny;	
			points3Array = points3_ny;
		}
		break;
	case "penn":
		if (typeof points1_penn === 'undefined') {
			alert("no array called points1_penn in points1.js");
		} else {
			points1Array = points1_penn;
			points2Array = points2_penn;	
			points3Array = points3_penn;
		}
		break;
	case "roadSet1Formatted":
		if (typeof points1_roadSet1Formatted === 'undefined') {
			alert("no array called points1_roadSet1Formatted in points1.js");
		} else {
			points1Array = points1_roadSet1Formatted;
			points2Array = points2_roadSet1Formatted;	
			points3Array = points3_roadSet1Formatted;
		}
		break;
	}
	loadMaps();
}

function load() {
	    // Default the selector to roadSet1Formatted.txt
	    var sel = document.getElementById('selectInput');
	    sel.value = "roadSet1Formatted";

	    // Load the maps
	    loadMaps();
}

function loadMaps() {
	//Djikstra's Shortest Path
	loadDjikstras();
        		
    //Minimum Spanning Tree
    loadMST();
       
     //BFS
	loadBFS();
}

function loadDjikstras() {
	var centerLatLng = new google.maps.LatLng(points1Array[0], points1Array[1]);
	var mapOptions = {
	          zoom: 16,
			  center: centerLatLng,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        }
	var map = new google.maps.Map(document.getElementById('map1'), mapOptions);
	
	var vertices = new Array();
	for (var i = 0; i < points1Array.length - 1; i=i+2) {
    	var point = new google.maps.LatLng(points1Array[i], points1Array[i+1]);
    	createLetteredMarker(points1Array, point, i/2, map);
    	vertices[i/2] = point;
    }
	var polyline = new google.maps.Polyline({
		path: vertices,
		strokeColor: "#6600FF",
		strokeOpacity: 0.5,
		strokeWeight: 10
	});
	polyline.setMap(map);
}

function loadMST() {
	// Centering isn't quite right
	var centerLatLng = new google.maps.LatLng(points2Array[0], points2Array[1]);
	var mapOptions = {
	          zoom: 16,
			  center: centerLatLng,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        }
	var map = new google.maps.Map(document.getElementById('map2'), mapOptions);

	var counter = 0;
	var vertices = new Array();

    for (var i = 0; i < points2Array.length-1; i=i+2) {
		var point = new google.maps.LatLng(points2Array[i],points2Array[i+1]);
		createMarker(point, map);
		vertices[counter]=point;
		counter++;
	}
		
	for (var i = 2; i <= vertices.length; i=i+2) {
		var polyline = new google.maps.Polyline({
			path: [vertices[i-2], vertices[i-1]],
			strokeColor: "#6600FF",
			strokeOpacity: 0.5,
			strokeWeight: 10
		});
		polyline.setMap(map);
	}
}

function loadBFS() {
	var centerLatLng = new google.maps.LatLng(points3Array[0], points3Array[1]);
	var mapOptions = {
	          zoom: 16,
			  center: centerLatLng,
	          mapTypeId: google.maps.MapTypeId.ROADMAP
	        }
	var map = new google.maps.Map(document.getElementById('map3'), mapOptions);
    
	for(var i = 0; i < points3Array.length; i=i+4){
   		var vertices3 = new Array();
    	var point = new google.maps.LatLng(points3Array[i], points3Array[i+1]);
    	vertices3[0] = point;
    	var point2 = new google.maps.LatLng(points3Array[i+2], points3Array[i+3]);
    	vertices3[1] = point2;
		createMarker(point2, map);
        
		var polyline = new google.maps.Polyline({
			path: vertices3,
			strokeColor: "#6600FF",
			strokeOpacity: 0.5,
			strokeWeight: 10
		});
		polyline.setMap(map);
    }
}

var iterator;
var isAnimated = false;

// Animate the bread first search by delaying the drawing of each subsequent polyline.
function animateBFS() {
	if (!isAnimated) {
		isAnimated = true;
		var centerLatLng = new google.maps.LatLng(points3Array[0], points3Array[1]);
		var mapOptions = {
		          zoom: 16,
				  center: centerLatLng,
		          mapTypeId: google.maps.MapTypeId.ROADMAP
		        }
		var map = new google.maps.Map(document.getElementById('map3'), mapOptions);

		var vertices = new Array();
		iterator = 0;
		for (var i = 0; i < points3Array.length - 1; i=i+4) {
			var point = new google.maps.LatLng(points3Array[i], points3Array[i+1]);
			var point2 = new google.maps.LatLng(points3Array[i+2], points3Array[i+3]);
			createMarker(point2, map);

			setTimeout(function() {
				animateBFSLines(points3Array, map);
			          }, i * 100);
	    }
	}
}

function animateBFSLines(pointsArray, map) {
	var point = new google.maps.LatLng(points3Array[iterator], points3Array[++iterator]);
	var point2 = new google.maps.LatLng(points3Array[++iterator], points3Array[++iterator]);
        
	var polyline = new google.maps.Polyline({
		path: [point, point2],
		strokeColor: "#6600FF",
		strokeOpacity: 0.5,
		strokeWeight: 10
	});
	polyline.setMap(map);
	
	if (iterator == pointsArray.length - 1) {
		isAnimated = false;
	} else {
		iterator++;
	}
}

// Creates a marker for Djikstras whose info window displays the letter corresponding
// to the given index.
function createLetteredMarker(array, point, index, map) { 
	/* Create a lettered icon for this point using our icon class.
  	* 
	* If the point is the starting point, then we will set the image to be a special
	* starting-point icon. Likely we will do the same if the point is the end point.
	* For all other points, we will use an icon image with a letter alphabetically corresponding
	* to the index
	*/
	var letter = String.fromCharCode("A".charCodeAt(0) + index);
	var lat = point.lat();
	var lng = point.lng();
	var isStart = false;
	var isEnd = false;

    if (isNaN(lat) || isNaN(lng)) {
        return;
    }

	var iconUrl = "http://www.google.com/mapfiles/marker" + letter + ".png";
   	if (lat == array[0] && lng == array[1]) {
		isStart = true;
  		iconUrl = "http://www.google.com/mapfiles/dd-start.png";
   	} else if (lat == array[array.length-2] && lng == array[array.length-1]) {
   		isEnd = true;
		iconUrl = "http://www.google.com/mapfiles/dd-end.png";
   	}

	var markerInfo = "Point " + letter + "<br>Coordinates: (" + lat +","+ lng +")";
   	if (isStart) {
		markerInfo = "<b>Start Point</b><br>" + "Point " + letter + "<br>Coordinates: (" + lat +","+ lng +")";
   	} else if (isEnd) {
   		markerInfo = "<b>End Point</b><br>" + "Point " + letter + "<br>Coordinates: (" + lat +","+ lng+")";
	}
  
	var markerInfoWindow = new google.maps.InfoWindow({
		content: markerInfo
	});
	
	var marker = new google.maps.Marker({
		position: point,
		map: map,
		icon: iconUrl,
	});
	
	google.maps.event.addListener(marker, 'click', function() {
		markerInfoWindow.open(map, marker);
	});
}

// Creates a marker whose info window displays the coordinates of the marker.
function createMarker(point, map) {
	var markerInfoWindow = new google.maps.InfoWindow({
		content: "Coordinates: (" + point.lat() +","+ point.lng() +")"
	});
	
	var marker = new google.maps.Marker({
		position: point,
		map: map
	});
	
	google.maps.event.addListener(marker, 'click', function() {
		markerInfoWindow.open(map, marker);
	});
}
