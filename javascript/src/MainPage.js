import React, { Component } from 'react';
import NavigationPannel from "./NavigationPannel";

class MainPage extends Component{
	constructor(props){
		super(props);
		this.state= {current:"connexion", connected: false};
		this.getConnected = this.getConnected.bind(this);
		this.setLogout = this.setLogout.bind(this);
	}	

	getConnected(){
		this.setState({current:"tweet_wall", connected: true, sign: false});
	}
	
	setLogout(){
		this.setState({current:"connexion", connected: false, sign: false});
	}

	render(){
		return (<div className="MainPage"> 
			<NavigationPannel 
			login={this.getConnected}
			logout={this.setLogout}
			isConnected={this.state.connected} 
			signup={this.state.sign}/>
			</div>);
	}
}

export default MainPage;
