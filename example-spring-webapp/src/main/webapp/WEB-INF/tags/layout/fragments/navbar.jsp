<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span
	class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="#">Spring Example App</a>
<div class="nav-collapse collapse">
	<ul class="nav">
		<li class="active"><a href="<c:url value="/" />">Home</a></li>
		<li><a href="<c:url value="/a" />">A</a></li>
		<li><a href="<c:url value="/b" />">B</a></li>
		<li><a href="<c:url value="/c" />">C</a></li>
		<li><a href="<c:url value="/d" />">D</a></li>
	</ul>
</div>