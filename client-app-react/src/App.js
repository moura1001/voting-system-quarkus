import React from "react";
import _ from 'lodash';

import logo from './logo.svg';
import './App.css';

function Election() {
  const [data, setData] = React.useState([]);

  React.useEffect(() => {
    let eventSource = new EventSource("http://vote.dio.localhost");
    eventSource.onmessage = e => {
      updateElection(JSON.parse(e.data));
    }
  }, []);

  const updateElection = (election) => {
    setData([...election])
  }

  return (
    <div>
      {data.map((election) =>
        <div key={election.id}>
          <h1>Election {election.id}</h1>

          <div className="election">
            {_.orderBy(election.candidates, ['votes', 'fullName'], ['desc', 'asc']).map((candidate) =>
            <div key={candidate.id} className="card" data-id={candidate.id}>
              <div className="votes">{candidate.votes}</div>
              <div className="profile">
                <img src={candidate.photo} alt={candidate.fullName} className="thumbnail" width="180" height="180"/>
              </div>
              <h3 className="name">{candidate.fullName}</h3>
              <p className="title">{candidate.jobTitle}</p>
              <a className="description" href={"mailto:" + candidate.email} target="_blank" rel="noreferrer">{candidate.email}</a>
              { candidate.phone ? <a className="description" href={"tel:" + candidate.phone} target="_blank" rel="noreferrer">{candidate.phone}</a> : ''}
            </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

function App() {
  return (
    <div>
      <header>
        <img src={logo} alt="logo" />
      </header>
      <main>
        <Election />
      </main>
    </div>
  );
}

export default App;
