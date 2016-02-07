<%@ include file="/WEB-INF/jsp/includes/siteTags.jsp"%>
    <%-- Navigation --%>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value='/travel/planBoard.go'/>">my<i class="fa fa-motorcycle fa-sm"></i>TravelPal&nbsp;<i class="fa fa-train"></i></a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                       <a href="<c:url value='/travel/plans.go'/>">Plan</a>
                    </li>  
                </ul>
                <ul class="nav navbar-nav pull-right">
                   <li><a href="http://abuabdul.com">&copy; abuabdul.com 2013-2016</a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>