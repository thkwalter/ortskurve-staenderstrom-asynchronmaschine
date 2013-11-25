function ortskurveZeichnen(context, centerX, centerY, radius) 
   {   
   // Die Ortskurve wird eingezeichnet
   context.beginPath();
   context.arc(centerX, centerY, radius, 0, 2 * Math.PI, false);
   context.lineWidth = 1;
   context.strokeStyle = '#00cc00';
   context.stroke();
   
   // Der Mittelpunkt wird eingezeichnet
   context.beginPath();
   context.moveTo(centerX-5, centerY);
   context.lineTo(centerX+5, centerY);
   context.moveTo(centerX, centerY-5);
   context.lineTo(centerX, centerY+5);
   context.strokeStyle = '#00cc00';
   context.stroke();
   }