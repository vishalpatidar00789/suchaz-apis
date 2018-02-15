import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ConsumerProfileSuchaz } from './consumer-profile-suchaz.model';
import { ConsumerProfileSuchazService } from './consumer-profile-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-consumer-profile-suchaz',
    templateUrl: './consumer-profile-suchaz.component.html'
})
export class ConsumerProfileSuchazComponent implements OnInit, OnDestroy {
consumerProfiles: ConsumerProfileSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private consumerProfileService: ConsumerProfileSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.consumerProfileService.query().subscribe(
            (res: HttpResponse<ConsumerProfileSuchaz[]>) => {
                this.consumerProfiles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInConsumerProfiles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ConsumerProfileSuchaz) {
        return item.id;
    }
    registerChangeInConsumerProfiles() {
        this.eventSubscriber = this.eventManager.subscribe('consumerProfileListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
