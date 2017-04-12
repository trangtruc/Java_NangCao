function formatMVField(s)   
{   
    var aVals = s.split(";#"); //Split the incoming string based on the delimiter specified   
    var iL = aVals.length; //Get the number of values available   
    if (iL > 1)    
    {   
        //Begin writing out the HTML   
        document.write("<div style='display:block'>");   
        for (var i=1;i<iL;i+= 2)   
        {   
            document.write("<div style='white-space:nowrap'>" + aVals[i] + "</div>");   
        }   
        document.write("</div>");
    }   
} 

function formatMVFieldnew(s1,s2,s3)   
{   
    var aVals = s1.split(";#"); //Split the incoming string based on the delimiter specified   
    var aVals2 = s2.split(";#");
    var aVals3 = s3.split(";#");
    
    var iL = aVals.length; //Get the number of values available   
    var socot = iL/2;
    if (iL > 1)    
    {   
        //Begin writing out the HTML   
        //document.write("<table cellpadding='0' cellspacing='0' width='620px' border='1'>");   
        document.write("<tr><td class='headerproperties' width='170px' rowspan='"+ socot +"' valign='top'>Cơ quan ban hành/ Người ký/ Chức danh</td>");   
        document.write("<td width='300px' style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;'>" + aVals[1] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='150px'>" + aVals2[1] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='100px'>" + aVals3[1] + "</td>");   
        document.write("</tr>");
        for (var i=3;i<iL;i+= 2)   
        {   
            document.write("<tr><td width='300px' style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;'>" + aVals[i] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='150px'>" + aVals2[i] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='100px'>" + aVals3[i] + "</td></tr>");   
        }   
        //document.write("</table>");
    }   
} 

function formatMVFieldnewEn(s1,s2,s3)   
{   
    var aVals = s1.split(";#"); //Split the incoming string based on the delimiter specified   
    var aVals2 = s2.split(";#");
    var aVals3 = s3.split(";#");
    
    var iL = aVals.length; //Get the number of values available   
    var socot = iL/2;
    if (iL > 1)    
    {   
        //Begin writing out the HTML   
        //document.write("<table cellpadding='0' cellspacing='0' width='620px' border='1'>");   
        document.write("<tr><td class='headerproperties' width='170px' rowspan='"+ socot +"' valign='top'>Issuing body/ Signer/Office</td>");   
        document.write("<td width='300px' style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;'>" + aVals[1] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='150px'>" + aVals2[1] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='100px'>" + aVals3[1] + "</td>");   
        document.write("</tr>");
        for (var i=3;i<iL;i+= 2)   
        {   
            document.write("<tr><td width='300px' style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;'>" + aVals[i] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='150px'>" + aVals2[i] + "</td><td style='border-bottom:solid 1px #d1d1d1;border-right:solid 1px #d1d1d1;padding:5px;font-family:Arial;font-size:9pt;' width='100px'>" + aVals3[i] + "</td></tr>");   
        }   
        //document.write("</table>");
    }   
} 


function formatMVFieldVBCanCu(s)   
{   
    var aVals = s.split(";#"); //Split the incoming string based on the delimiter specified   
    var iL = aVals.length; //Get the number of values available   
    if (iL > 1)    
    {   
        //Begin writing out the HTML   
        document.write("<div style='display:block;width:100%;'><ul>");   
        for (var i=1;i<iL;i+= 2)   
        {   
        	var vbID = aVals[i-1].replace("IDVB","");
            document.write("<li style='background:transparent url(/PublishingImages/images2/note_red_square.gif) no-repeat scroll 8px 14px;color:#004899;font-family:Arial,Helvetica,sans-serif;font-size: 12px;padding:8px 13px 8px 18px;'><a target='_blank' href='/vbpq/Lists/Vn%20bn%20php%20lut/View_Detail.aspx?ItemID="+ vbID + "'>" + aVals[i] + "</a></li>");   
        }   
        document.write("</ul></div>");
    }   
} 

function formatMVField111(s)   
{   
    var aVals = s.split(";#"); //Split the incoming string based on the delimiter specified   
    var iL = aVals.length; //Get the number of values available   
    if (iL > 1)    
    {   
        //Begin writing out the HTML   
        document.write("<div style='display:block'>");   
        for (var i=1;i<iL;i+= 2)   
        {   
            document.write("<div style='white-space:nowrap'>" + aVals[i] + "</div>");   
        }   
        document.write("</div>");
    }   
} 



function SwitchTab(obj)
        {
        switch (obj)
            {
                case 'T1viVN':
                {
                    document.getElementById('T1').className='active';                            
                    document.getElementById('T2').className='no_active';                            
                    document.getElementById('T1viVN').style.display = 'block';                            
                    document.getElementById('T2hnVn').style.display = 'none';
                    break;
                }
                case 'T2hnVn':
                {
                     
                    document.getElementById('T1').className='no_active';                            
                    document.getElementById('T2').className='active';                                                         
                    document.getElementById('T1viVN').style.display = 'none';                            
                    document.getElementById('T2hnVn').style.display = 'block';                                                        
                    break;
                }                        
            }           
        }    


var flag = 1;
function execute(element) { //current element
    if (flag == 1) {
        show(element);
        flag= 0;
    } else if (flag == 0) {
        hide(element);
        flag= 1;
    }
                
}
function show(element) {
    var tag = document.getElementById(element);
    if (tag == null) return;
    tag.style.display = '';
}
function hide(element) {
    var tag = document.getElementById(element);
    if (tag == null) return;
    tag.style.display = 'none';
}