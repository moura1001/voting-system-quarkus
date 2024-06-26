import random

from locust import HttpUser, task, between

class MyVote(HttpUser):
    wait_time = between(1, 5)
    
    @task
    def voting(self):
        for election in self.client.get("/api/voting").json():
            election_id = election['id']
            candidate_id = random.choice(election['candidates'])
            
            self.client.post(f"/api/v1/voting/elections/{election_id}/candidates/{candidate_id}")

# locust -H http://vote.dio.localhost
# locust --headless --users 1 --spawn-rate 1 -H http://vote.dio.localhost
