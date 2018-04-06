import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ConsumerProfileHistorySuchaz } from './consumer-profile-history-suchaz.model';
import { ConsumerProfileHistorySuchazService } from './consumer-profile-history-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-consumer-profile-history-suchaz',
    templateUrl: './consumer-profile-history-suchaz.component.html'
})
export class ConsumerProfileHistorySuchazComponent implements OnInit, OnDestroy {
consumerProfileHistories: ConsumerProfileHistorySuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private consumerProfileHistoryService: ConsumerProfileHistorySuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.consumerProfileHistoryService.query().subscribe(
            (res: HttpResponse<ConsumerProfileHistorySuchaz[]>) => {
                this.consumerProfileHistories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInConsumerProfileHistories();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ConsumerProfileHistorySuchaz) {
        return item.id;
    }
    registerChangeInConsumerProfileHistories() {
        this.eventSubscriber = this.eventManager.subscribe('consumerProfileHistoryListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
