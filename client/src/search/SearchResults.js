import React, { Component } from "react";


export default class SearchResults extends Component {

    render() {
        const data = this.props.data;
        if (! data || data.length < 1) {
            return null;
        }
        return (
            <div className="search-result-container">
                <ul>
                    {data.map(function(v){
                        return <li key={v.id}>{v.meaning}</li>;
                    })}
                </ul>
            </div>
        );
    }
}

SearchResults.propTypes = {
    data: React.PropTypes.array
};
