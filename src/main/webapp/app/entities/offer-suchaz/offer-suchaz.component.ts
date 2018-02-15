import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { OfferSuchaz } from './offer-suchaz.model';
import { OfferSuchazService } from './offer-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-offer-suchaz',
    templateUrl: './offer-suchaz.component.html'
})
export class OfferSuchazComponent implements OnInit, OnDestroy {
offers: OfferSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private offerService: OfferSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.offerService.query().subscribe(
            (res: HttpResponse<OfferSuchaz[]>) => {
                this.offers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInOffers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: OfferSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInOffers() {
        this.eventSubscriber = this.eventManager.subscribe('offerListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
