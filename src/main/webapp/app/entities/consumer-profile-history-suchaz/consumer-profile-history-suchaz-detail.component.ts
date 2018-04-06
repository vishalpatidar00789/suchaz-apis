import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ConsumerProfileHistorySuchaz } from './consumer-profile-history-suchaz.model';
import { ConsumerProfileHistorySuchazService } from './consumer-profile-history-suchaz.service';

@Component({
    selector: 'jhi-consumer-profile-history-suchaz-detail',
    templateUrl: './consumer-profile-history-suchaz-detail.component.html'
})
export class ConsumerProfileHistorySuchazDetailComponent implements OnInit, OnDestroy {

    consumerProfileHistory: ConsumerProfileHistorySuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private consumerProfileHistoryService: ConsumerProfileHistorySuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInConsumerProfileHistories();
    }

    load(id) {
        this.consumerProfileHistoryService.find(id)
            .subscribe((consumerProfileHistoryResponse: HttpResponse<ConsumerProfileHistorySuchaz>) => {
                this.consumerProfileHistory = consumerProfileHistoryResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInConsumerProfileHistories() {
        this.eventSubscriber = this.eventManager.subscribe(
            'consumerProfileHistoryListModification',
            (response) => this.load(this.consumerProfileHistory.id)
        );
    }
}
