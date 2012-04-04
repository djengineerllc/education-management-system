<div style="height:6px;"></div>
<div style="text-align:center"><b>教师分表(<@code codeType='Term' data=termId />)</b></div>
<div style="height:12px;"></div>
<table border="1" cellpadding="0" cellspacing="0" width="100%">
	<thead>
		<tr>
			<th style="text-align:center;" rowspan="2">教师</th>
			<th style="text-align:center;" rowspan="2">课时</th>
			<th style="text-align:center;" rowspan="2">单双</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周一</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周二</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周三</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周四</th>
			<th style="text-align:center;" rowspan="1" colspan="3">周五</th>
		</tr>
		<tr>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
			<th style="text-align:center;">课程</th>
			<th style="text-align:center;">班级</th>
			<th style="text-align:center;">教室</th>
		</tr>
	</thead>
	<tbody>
		<#list items as item>
		<tr>
			<#if (item_index % 10) == 0>
			<td rowSpan="10">${item.teacher}</td>
			</#if>
			<#if (item_index % 2) == 0>
			<td rowSpan="2">${item.classCode}</td>
			</#if>
			<td>${item.oeInd}</td>
			<td>${item.monCourse}</td>
			<td>${item.monClass}</td>
			<td>${item.monRoom}</td>
			<td>${item.tueCourse}</td>
			<td>${item.tueClass}</td>
			<td>${item.tueRoom}</td>
			<td>${item.webCourse}</td>
			<td>${item.webClass}</td>
			<td>${item.webRoom}</td>
			<td>${item.thuCourse}</td>
			<td>${item.thuClass}</td>
			<td>${item.thuRoom}</td>
			<td>${item.friCourse}</td>
			<td>${item.friClass}</td>
			<td>${item.friRoom}</td>
		</tr>
		</#list>
	</tbody>
</table>