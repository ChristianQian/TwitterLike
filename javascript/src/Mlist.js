import React, { Component } from 'react';
import './form.css';

export default class Mlist extends Component{
	constructor(props){
		super(props);
		console.log(this.props.l)
	}


    render() {
        return (
      <div className="vc" ref="iScroll"
        style={{margin:"3px 10px 3px 3px", height: "window.innerHeight", overflowY: "auto" }}>
        		<div>
						{ this.props.l.map(u => {
							return <div className="boxmess">
								{u.text}<br/>{u.author_nom} {u.author_prenom} | {u.date}
							</div>
						})}
        	  </div>
      </div>
    );
} 
}