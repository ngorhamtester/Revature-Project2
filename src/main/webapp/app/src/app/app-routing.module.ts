import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { InjectionToken } from '@angular/core';
import { ActivatedRouteSnapshot } from '@angular/router';

const externalUrlProvider = new InjectionToken('externalUrlRedirectResolver');

const routes: Routes = [
	{
		path: "SpotifyLogin",
		resolve:
		{
			url: externalUrlProvider
		},
		component: LoginComponent
	},
	{
		path: "",
		component: LoginComponent
	},
	{
		path: "dashboard",
		component: DashboardComponent
	}
];

@NgModule({
  providers: [
    {
	  provide: externalUrlProvider,
	  //A function that gets the activated route, reads a route parameter 
	  //called externalUrl, then uses window.open to navigate to the provided 
	  //link.
	  useValue: (route: ActivatedRouteSnapshot) =>
	  {		//When calling router.navigate, we will pass in externalUrl:url as a parameter
			//This will define where we are navigating to.
		  const externalUrl = route.paramMap.get('externalUrl');
		  //Navigate to the specified url.
		  window.open(externalUrl, '_self');
	  }
	}
  ],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
