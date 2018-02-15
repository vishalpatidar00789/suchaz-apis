import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ConsumerProfileSuchaz } from './consumer-profile-suchaz.model';
import { ConsumerProfileSuchazService } from './consumer-profile-suchaz.service';

@Component({
    selector: 'jhi-consumer-profile-suchaz-detail',
    templateUrl: './consumer-profile-suchaz-detail.component.html'
})
export class ConsumerProfileSuchazDetailComponent implements OnInit, OnDestroy {

    consumerProfile: ConsumerProfileSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private consumerProfileService: ConsumerProfileSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConsumerProfiles();
    }

    load(id) {
        this.consumerProfileService.find(id)
            .subscribe((consumerProfileResponse: HttpResponse<ConsumerProfileSuchaz>) => {
                this.consumerProfile = consumerProfileResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConsumerProfiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'consumerProfileListModification',
            (response) => this.load(this.consumerProfile.id)
        );
    }
}
