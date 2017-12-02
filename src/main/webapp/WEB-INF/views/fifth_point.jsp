<form method="POST" action="/sixth_point/" autocomplete="off">
	<fieldset class="floating-box">
		<legend>Tacka 5 od ${totalNumber} </legend>
		<table>
			<tr>
				<td>Unesi detalje za T${startPoint} </td>
			</tr>
			<tr>
				<td>azimut za T${previousPoint} </td>
				<td><input type="text" name="previousPointAngle" value="${entry.previousPointAngle}" size="5"/></td>
			</tr>
			<tr>
				<td>azimut za T${nextPoint} </td>
				<td><input type="text" name="nextPointAngle" value="${entry.nextPointAngle}" size="5" /></td>
			</tr>
			<tr>
				<td>azimut za tacku 1</td>
				<td><input type="text" name="point1Angle" value="${entry.point1Angle}" size="5"/></td>
			</tr>
			<tr>
				<td>duzinu za tacku 1</td>
				<td><input type="text" name="point1Length" value="${entry.point1Length}" size="5" /></td>
			</tr>
			<tr>
				<td>azimut za tacku 2</td>
				<td><input type="text" name="point2Angle" value="${entry.point2Angle}" size="5"/></td>
			</tr>
			<tr>
				<td>duzinu za tacku 2</td>
				<td><input type="text" name="point2Length" value="${entry.point2Length}" size="5" /></td>
			</tr>
			<tr>
				<td>azimut za tacku 3</td>
				<td><input type="text" name="point3Angle" value="${entry.point3Angle}" size="5"/></td>
			</tr>
			<tr>
				<td>duzinu za tacku 3</td>
				<td><input type="text" name="point3Length" value="${entry.point3Length}" size="5" /></td>
			</tr>
		</table>
        <input type="submit" name="buttonAction" value="Nastavi" class="button"/>
	</fieldset>
</form>
