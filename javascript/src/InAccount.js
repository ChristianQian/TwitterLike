import React, { Component } from 'react';
import axios from 'axios';
import bon from './bon.png';
import './form.css';
import Slist from './Slist';
import Mlist from './Mlist';
import Flist from './Flist';

export default class InAccount extends Component{
	constructor(props){
		super(props);
		this.state={key:this.props.kv,text:"",want:false,alluser:[],friends:[],comments:[]};
		this.getUsers();
		this.getFriends();
		this.getComments();
		this.key=this.props.kv;
		this.ajouterDansAmis= this.ajouterDansAmis.bind(this);
		this.ajouterDansEtranger= this.ajouterDansEtranger.bind(this);

	}

	getUsers = async ( ) => {
		axios.get("http://localhost:8080/tme2/AllUser?id="+this.props.id).then(res =>{
			this.setState({alluser:res.data});
		}).catch(function(e) {console.log(e)});	
	}

	getFriends = async ( ) => {
		axios.get("http://localhost:8080/tme2/AllFriends?id="+this.props.id).then(res =>{			
			this.setState({friends:res.data});
		}).catch(function(e) {console.log(e)});	
	}

	getComments = async ( ) => {
		axios.get("http://localhost:8080/tme2/AllComment?id="+this.props.id).then(res =>{			
			this.setState({comments:res.data});
		}).catch(function(e) {console.log(e)});	
	}

	ajouterDansAmis(e,u){
		this.setState({friends:this.state.friends.concat(u),alluser: this.state.alluser.filter(u =>
			u.id !== e
	   )})
	}

	ajouterDansEtranger(e,u){
		this.setState({alluser:this.state.alluser.concat(u),friends: this.state.friends.filter(u =>
			 u.id !== e
		)})
	}

	onChange = e => this.setState({ [e.target.name]: e.target.value })

	deconnexion(){
		axios.get("http://localhost:8080/tme2/Logout?key="+this.props.kv).then(response=>{
		if(response.data.nom){
			alert("Vous êtes bien deconnecté | "+response.data.nom+" "+response.data.prenom+" |");
			this.props.deco();
		}else
			alert("Attention, vous êtes toujours deconnecté");
		}).catch(e=>{alert(e)});

	}
	
	publier(){
		if(this.state.text !== ""){
			axios.get("http://localhost:8080/tme2/AddComment?key="+this.props.kv+"&text="+this.state.text).then(response=>{
			if(response.data.user){
				this.getComments();
				console.log("comment ajoute")
			}else
				console.log("comment non ajouté")
			}).catch(e=>{alert(e)});
		}
	}

	render(){
		return (
		<div className="InAccount">
			<header >
				
				<img className="img" src={bon} alt='logo'/>
				
					<div className="zone_recherche">
					
						<div className="form-group" style={{position:"relative",display: "flex"}}>
   				 		<input type="text" className="form-control" id="formGroupExampleInput" placeholder="Rechercher"/>
						<button id="recherche_button" >Recherche</button>
				 		</div>
					
					
						<div>
						<input type="text" name="text" autoComplete="off" placeholder='Publier un message' value={this.state.text} onChange={this.onChange} />
						<button type="button" onClick={()=>this.publier()}>Publier</button>
				 		</div>
					
					</div>
				
				<div style={{position: "absolute", right: "2%", top:"10px"}}>
				<button className="login-btn"  onClick={()=>this.deconnexion()}> Deconnexion</button>
				</div>
      </header>
			
				<div className="stat">
				<Slist l={this.state.alluser} k1={this.state.key} a={this.ajouterDansAmis} m={this.getComments}/>
				</div>
				<div className="message">
				<Mlist l={this.state.comments}/>
				</div> 
				<div className="friend"> 
				<Flist l={this.state.friends} k2={this.state.key} ae={this.ajouterDansEtranger} m={this.getComments}/>
				</div>
			</div>);
	}
}

