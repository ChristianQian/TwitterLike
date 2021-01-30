import React, { Component } from 'react';
import Login from './Login';
import InAccount from './InAccount';
import Inscription from './Inscription';

class NavigationPannel extends Component{
	constructor(props){
		super(props);
		this.state = ({co:this.props.isConnected ,wantSign:false, ke:"",id:""});
		this.setSign = this.setSign.bind(this);
		this.setId= this.setId.bind(this);
		this.setKey= this.setKey.bind(this);
	}

	setId(e){
		this.setState({id:e});
	}

	setKey(kv){
		this.setState({ke:kv});
	}

	setSign(){
		if (this.state.wantSign === true){
			this.setState({wantSign:false});
		}else{
			this.setState({wantSign:true});
		}	
	}

	render(){
		if(this.props.isConnected === false){
			if(this.state.wantSign === true){
				return <div className="NavigationPannel">
				<nav>
					<Inscription  ws={this.setSign}/>
				</nav>
				</div>
			}
			return <div className="NavigationPannel">
			<nav>
			<Login c={this.props.login} id={this.setId} k={this.setKey}
			ws={this.setSign}/>
			</nav></div>;
		}

		return (<div className="NavigationPannel">
			<nav>
			<InAccount deco={this.props.logout} id={this.state.id} kv={this.state.ke}/>
		</nav></div>);
	}
}

export default NavigationPannel;
