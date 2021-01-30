import React, { Component } from 'react';
import axios from 'axios';
import './form.css';

class Login extends Component{
	constructor(props){
		super(props);
		this.state = ({login:"", pw:""});
		this.onChange = this.onChange.bind(this);
	}

	onChange = e => this.setState({ [e.target.name]: e.target.value })
	  
	send(){
		if(this.state.login !=='' && this.state.pw !==''){
        	axios.get("http://localhost:8080/tme2/Login?login="+this.state.login+"&password="+this.state.pw).then(response=>{
			if(response.data.none)
				alert("Veuillez remplir tous les champs");
			else if(response.data.unknown)
				alert("Login non reconnu");
			else if(response.data.wpw)
				alert("Mot de passe incorrect");
			else{
				this.props.k(response.data.key);
				this.props.id(response.data.id);
				this.props.c();
			}
			}).catch(e=>{alert(e)})
		}else
			alert("Veuillez remplir tous les champs");
	}

	render(){
		return (
			<div className="inner-container">
			<div className="Login">
			 <div className="box">
			<div className="input-group">
			<input type="text" name="login" autoComplete="off" className="login-input" placeholder='Username' value={this.state.login} onChange={this.onChange} /></div>
			<div className="input-group">
			<input type="password" name="pw" autoComplete="off" className="login-input" placeholder='Password' value={this.state.pw} onChange={this.onChange}/></div>
			
			<button type="button" className="login-btn" onClick={()=>this.send()}>Connexion</button>
			
			<br/>
			<label > Pas de compte? </label> 
			<button type="button" className="login-btn" onClick={this.props.ws}>Inscription</button>
			</div>
			</div>
			</div>);
	}
}

export default Login;
