
$(document).ready(function() {
	
	var url = window.location;
	//define buttons actions
	$("#updateAPIKey").click(function(event) {
		event.preventDefault();
		ajaxUpdateApiKey();
	})
	
	$("#getClosestNEO").click(function(event) {
		event.preventDefault();
		ajaxGetClosestNEO();
	})
	
	
	$("#getLargestNEO").click(function(event) {
		event.preventDefault();
		ajaxGetLargestNEO();
	})

	function openModel() {
		$("#modalId").modal();
	}


	/**
	 * Alert that action was successful.
	 * @param message message
	 */
	
	function alertSuccessful(message) {alertType(message,'success');}
	function alertInfo(message) {alertType(message,'info');}
	function alertWarning(message) {alertType(message,'warning');}
	function alertDanger(message) {alertType(message,'danger');}
	
	function alertType(message,type) {
		if (['success','info', 'warning', 'danger'].includes(type)<0) return;
	    $('#alert').html("<div class='alert alert-"+type+"'>"+message+
	    		"<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></div>");
	    $('#alert').show();
	  }
	
	/**
	 * Check if the object (list of flatneo objects) is valid
	 */
	function isNeoDataListReponseValid(data){
		var valid=true;
		$.each(data, function (index, flatneo) {
			valid=valid && 'selfLink' in flatneo && 'name' in flatneo; //check for needed fields
		});
		return valid;
	}
	
	//Check if the detailed NEO data is valid
	function isDetailedNEODataValid(data) {
		var valid=true;
		valid = valid && 'name' in data && 'close_approach_data' in data && data.close_approach_data.length>0;
		return valid;
	}
	
	//Generate Detailed NEO view given data from a rest call to /neodata/detailedneo/3650046
	function genHTMLForDetailedView(data){
		
		//variables will store the contents of tabs
		var $overviewPane='';
		var $orbitalData='';
		var $animation='';
		
		$overviewPane += '<table class="table table-condensed table-responsive" id="neoDetailedTableOverview">\n';
		$overviewPane += '<tr><td>Name</td><td>'+data.name+'</td></tr>\n';
		$overviewPane += '<tr><td>Potentially Hazardous</td><td>'+(data.potentiallyHazardousAsteroid?"<strong>Yes</strong>":"No")+'</td></tr>\n';
		$overviewPane += '<tr><td>NEO ID</td><td>'+data.neo_reference_id+'</td></tr>\n';
		$overviewPane += '<tr><td>Absolute Magnitude H. </td><td>'+data.absolute_magnitude_h+'</td></tr>\n';
		$overviewPane += '<tr><td>Estimated Diameter min (KM) </td><td>'+data.estimated_diameter.kilometers.estimated_diameter_min+'</td></tr>\n';
		$overviewPane += '<tr><td>Estimated Diameter max (KM) </td><td>'+data.estimated_diameter.kilometers.estimated_diameter_max+'</td></tr>\n';
		//loop through close approach data
		var closeApproaches=data.close_approach_data;
		$.each(closeApproaches, function(index, item){
			var aprDate=item.close_approach_date;
			var relVelocity = item.relative_velocity.kilometers_per_second;
			var missDistance = new Intl.NumberFormat().format(item.miss_distance.kilometers); //format 000,000 
			var closeAproachData = '<ul><li>Close Approach Date: '+aprDate+'</li><li>Rel. Velocity: '+relVelocity+' (KM/sec)</li><li>Miss Distance: '+missDistance+' (KM)</li>';
			$overviewPane += '<tr><td>Close Approach '+(index+1)+'</td><td>'+closeAproachData+'</td></tr>\n';
		})
		
		$overviewPane += '</table>';
		
		
		//generate orbital data
		$orbitalData += '<table class="table table-condensed table-responsive" id="neoDetailedTableOrbital">\n';
		$orbitalData += '<tr><td>Orbit ID </td><td>'+data.orbital_data.orbit_id+'</td></tr>\n';
		$orbitalData += '<tr><td>Orbit Determination Date </td><td>'+data.orbital_data.orbit_determination_date+'</td></tr>\n';
		$orbitalData += '<tr><td>Orbit Uncertainty </td><td>'+data.orbital_data.orbit_uncertainty+'</td></tr>\n';
		$orbitalData += '<tr><td>Minimum Orbit Intersection </td><td>'+data.orbital_data.minimum_orbit_intersection+'</td></tr>\n';
		$orbitalData += '<tr><td>Jupiter Tisserand Invariant </td><td>'+data.orbital_data.jupiter_tisserand_invariant+'</td></tr>\n';
		$orbitalData += '<tr><td>Epoch Osculation </td><td>'+data.orbital_data.epoch_osculation+'</td></tr>\n';
		$orbitalData += '<tr><td>Eccentricity </td><td>'+data.orbital_data.eccentricity+'</td></tr>\n';
		$orbitalData += '<tr><td>Semi-Major Axis </td><td>'+data.orbital_data.semi_major_axis+'</td></tr>\n';
		$orbitalData += '<tr><td>Inclination </td><td>'+data.orbital_data.inclination+'</td></tr>\n';
		$orbitalData += '<tr><td>Ascending Node Longitude </td><td>'+data.orbital_data.ascending_node_longitude+'</td></tr>\n';
		$orbitalData += '<tr><td>Orbital Period </td><td>'+data.orbital_data.orbital_period+'</td></tr>\n';
		$orbitalData += '<tr><td>Perihelion Distance </td><td>'+data.orbital_data.perihelion_distance+'</td></tr>\n';
		$orbitalData += '<tr><td>Perihelion Argument </td><td>'+data.orbital_data.perihelion_argument+'</td></tr>\n';
		$orbitalData += '<tr><td>Aphelion Distance </td><td>'+data.orbital_data.aphelion_distance+'</td></tr>\n';
		$orbitalData += '<tr><td>Perihelion Time </td><td>'+data.orbital_data.perihelion_time+'</td></tr>\n';
		$orbitalData += '<tr><td>Mean anomaly </td><td>'+data.orbital_data.mean_anomaly+'</td></tr>\n';
		$orbitalData += '<tr><td>Mean Motion </td><td>'+data.orbital_data.mean_motion+'</td></tr>\n';
		$orbitalData += '<tr><td>Equinox </td><td>'+data.orbital_data.equinox+'</td></tr>\n';
		
		//Compose an Animation tab
		//Just to be clear, I did not write the animation. 
		//I tried to play with the applet that Nasa had on NEO website (Orbit viewer 1.3)
		/**
		 * it takes following parameters:
		 * <param NAME="Eqnx" VALUE="2000.0">
			<param NAME="Epoch" VALUE="20170904.0">
			<param NAME="e" VALUE=".5882743297464178">
			<param NAME="Incl" VALUE="6.553903814343643">
			<param NAME="Peri" VALUE="225.9035323744481">
			<param NAME="Node" VALUE="190.1839210047635">
			<param NAME="M" VALUE="346.4330186894047">
			<param NAME="a" VALUE="2.496973777688111">
		 */
		//But the applet's code was too broken. I decided to build a web app instead... so embedding 
		//http://www.rankinstudio.com/Asteroid_Orbit_View in the tab.
		//It seems to like POST request via forms and not GET requests via query parameters.
		
		
		$animation += '<iframe name="animationframe" id="animationframe" class="framebody" src="http://www.rankinstudio.com/asteroids/asteroidsv2.9Arel.php"></iframe>';
		$animation += '<div class="panel clearfix">\n';
		$animation += '  <form id="animationform" action="http://www.rankinstudio.com/asteroids/asteroidsv2.9Arel.php" method="post">\n';
		$animation += '  <input type="hidden" name="PostType" id="PostType" value="byName">\n';
		$animation += '  <input type="hidden" name="ObjName" id="FormObjName" value="'+data.name+'">\n';
		$animation += '  <input type="submit" class="featuredAst" value="'+data.name+'">\n';
		$animation += '  </form>\n';
		
		var $detailedView ='';
		$detailedView += '<div>\n';
		$detailedView += '  <ul class="nav nav-tabs" role="tablist">\n';
		$detailedView += '    <li role="presentation" class="active"><a href="#overview" aria-controls="overview" role="tab" data-toggle="tab">Overview</a></li>\n';
		$detailedView += '    <li role="presentation"><a href="#orbitaldata" aria-controls="orbitaldata" role="tab" data-toggle="tab">Orbital Data</a></li>\n';
		//$detailedView += '    <li role="presentation"><a href="#animation" aria-controls="animation" role="tab" data-toggle="tab">Animation</a></li>\n';
		$detailedView += '  </ul>\n';
		$detailedView += '  <div class="tab-content">\n';
		$detailedView += '    <div role="tabpanel" class="tab-pane active" id="overview">'+$overviewPane+'</div>\n';
		$detailedView += '    <div role="tabpanel" class="tab-pane" id="orbitaldata">'+$orbitalData+'</div>\n';
		//$detailedView += '    <div role="tabpanel" class="tab-pane" id="animation">'+$animation+'</div>\n';
		$detailedView += '  </div>\n';
		$detailedView += '</div>\n';
		return $detailedView;
	}
	
	//function to do a post into an iframe containing animation
	//they seem to use the string name in brackets rather than asteroid id.
	function neoAnimationPostToFrame(neoNAME){
		url = 'http://www.rankinstudio.com/asteroids/asteroidsv2.9Arel.php';
		postToIframe('{"PostType":"byName", "ObjName":'+neoNAME+'}',url,'animationframe');
	}
	
	function postToIframe(data,url,target){
	    $('body').append('<form action="'+url+'" method="post" target="'+target+'" id="postToIframe"></form>');
	    $.each(data,function(n,v){
	        $('#postToIframe').append('<input type="hidden" name="'+n+'" value="'+v+'" />');
	    });
	    $('#postToIframe').submit().remove();
	}
	
	//puts data entries into NEO table
	function updateNEOTable(data, title){
		var $newtable="<table class='table table-responsive' id='neo_listing_table'>" +
			"<thead><tr>" +
			"<th>#</th>" +
			"<th>NEO ID</th>"+
			"<th>Name</th>"+
			"<th>Abs. Magnitude H.</td>"+
			"<th>min Est. Diameter (KM)</td>"+
			"<th>max Est. Diameter (KM)</td>"+
			"<th>Rel. Velocity(KM/sec)</td>"+
			"<th>Distance to(KM)</td>"+
			"<th>Orbiting Body</th>"+
			"<th>Hazardous Asteroid</th></thead>"+
			"<tbody>";
		
		$.each(data, function(index, neo){
			$newtable += '<tr>';
			$newtable += '<th scope="row">'+(index+1)+'</td>';
			$newtable += '<td>'+neo.referenceIde+'</td>';
			$newtable += '<td>'+neo.name+'</td>';
			$newtable += '<td>'+neo.absoluteMagnitude+'</td>';
			$newtable += '<td>'+neo.estimatedDiameterMin+'</td>';
			$newtable += '<td>'+neo.estimatedDiameterMax+'</td>';
			$newtable += '<td>'+neo.relativeVelocity+'</td>';
			var missDistance = new Intl.NumberFormat().format(neo.missDistance);
			$newtable += '<td>'+missDistance+'</td>';
			$newtable += '<td>'+neo.orbitingBody+'</td>';
			var isHazardous = neo.potentiallyHazardousAsteroid? "Yes":"No";
			$newtable += '<td>'+isHazardous+'</td></tr>';
		})
		
		$newtable += "</tbody></table>";
		
		$newPanel ='<div class="panel-group"><div class="panel panel-heading"><div class="panel-body">';
		$newPanel += title+'</div></div><div class="panel panel-default"><div class="panel-body">';
		$newPanel += $newtable+'</div></div></div>';
			
		$('#neotable').html($newPanel);
	}
	
	
	//generate modal view with the NEO details
	function getDetailsForNEOByID(neoID, name){
		if(neoID==null || name==null || !neoID>0 || !Number.isInteger(neoID)) 
			alertDanger("NEO detailed request parameters are invalid :(");
		else{
			$.ajax({
				type:"GET", url: "/neodata/detailedneo/"+neoID,
				contentType: 'application/json',
				success: function(data) {
					if(isDetailedNEODataValid(data)){
						alertSuccessful("Retrieved detailed description for NEO "+name);
						var header = "Near Earth Object: " + name;
						modalMsg(header, genHTMLForDetailedView(data));
						//enable animation
						$("#animationframe").on("load", function() {
							$("animationform").submit();
						});
					}else{
						alertDanger("Couldn't retrieve detailed description for NEO "+name);
					}
				},
				error: function(e) {alertDanger("Couldn't retrieve detailed description for NEO "+name);}
			})
		}
	}
	
	/**** get closest NEO ****/
	function ajaxGetClosestNEO(){
		//check if date is set
		if(apiCallDateStart==null || apiCallDateEnd==null) alertDanger("Please select the date range first!");
		else{
			$.ajax({
				type:"GET", url: "/neodata/getclosest/"+apiCallDateStart.format("YYYY-MM-DD")+"/"+apiCallDateEnd.format("YYYY-MM-DD"),
				contentType: 'application/json',
				success: function(data) {
					if (isNeoDataListReponseValid(data)){
						alertSuccessful("Retrieved closest objects for date range ");
						updateNEOTable(data, "Closest Near Earth Objects"); //even if data is empty, setting table to empty would be valid
						//check if data contains at least one entry, then do modal display for that object
						if(data.length>0){
							//try to do a second request to /neodata/detailedneo/neoID to get detailed information.
							var firstNEO = data[0];
							getDetailsForNEOByID(firstNEO.referenceId,
									firstNEO.name);
						}
					}
					else
						alertDanger("Error accessing /neodata/getclosest API");
				},
				error : function(e) {alertDanger("Error accessing /neodata/getclosest API");}
			})
		}
	}
	
	/**** get closest NEO ****/
	function ajaxGetLargestNEO(){
		//check if date is set
		if(apiCallDateStart==null || apiCallDateEnd==null) alertDanger("Please select the date range first!");
		else{
			$.ajax({
				type:"GET", url: "/neodata/getbiggest/"+apiCallDateStart.format("YYYY-MM-DD")+"/"+apiCallDateEnd.format("YYYY-MM-DD"),
				contentType: 'application/json',
				success: function(data) {
					if (isNeoDataListReponseValid(data)){
						alertSuccessful("Retrieved largest objects for date range ");
						updateNEOTable(data, "Largest Near Earth Objects"); //even if data is empty, setting table to empty would be valid
						//check if data contains at least one entry, then do modal display for that object
						if(data.length>0){
							//try to do a second request to /neodata/detailedneo/neoID to get detailed information.
							var firstNEO = data[0];
							getDetailsForNEOByID(firstNEO.referenceId,
									firstNEO.name);
						}
					}
					else
						alertDanger("Error accessing /neodata/getbiggest API");
				},
				error : function(e) {alertDanger("Error accessing /neodata/getbiggest API");}
			})
		}
	}
	
	/**** update API KEY ****/
	function ajaxUpdateApiKey() {
		$.ajax({
			type : "POST",
			url : "/apikey",
			contentType: 'application/json',
			data:  JSON.stringify({"apiKey":$("#apiKeyInput").val()}),
			success : function(data) {
				if(data.status == 'success')
					alertSuccessful("API Key was updated successfully");
				else
					alertDanger("Error updating API KEY!");
			},
			error : function(e) {alertDanger("Error updating API KEY!");}
		});
	}
	
	function modalMsg(header,data) {
		openModel();
			$(".modal-header").html('<h3 class=neoModalTitle>'+header+'</h3>');
			$(".modal-body #notificationPane").html(data);
	}
	
	//Work-around for datepicker initial state will not be set - have to set it manually.
	var apiCallDateStart = moment(); //default values for API date range calls
	var apiCallDateEnd = moment();
	updateDateRangeBox(apiCallDateStart,apiCallDateEnd);
	

	/*** date range picker ***/
	$('#daterangegroup').daterangepicker({
	    "ranges": {
	    		'Today': [moment(), moment()],
	        'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	        'This Month': [moment().startOf('month'), moment().endOf('month')],
	        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'July of 1900': [moment("1900-07-01"), moment("1900-07-31")],
			'Oct 2nd of 2019': [moment("2019-10-02"), moment("2019-10-02")]
	    },
	    "alwaysShowCalendars": true,
	    "autoApply": true,
	    "minDate": "01/01/1900"
	}, function(start, end, label) {
		updateDateRangeBox(start,end)
	});

	/**
	 * Format the date range text field  
	 * @param startDate start date
	 * @param endDate end date
	 */
	function updateDateRangeBox(startDate,endDate) {
		newrange = startDate.format('YYYY-MM-DD') + ' to ' + endDate.format('YYYY-MM-DD');
		console.log('New date range selected: ' + newrange);
		apiCallDateStart=startDate;
		apiCallDateEnd=endDate;
		$('#daterangetext').val(newrange);
	}
});



