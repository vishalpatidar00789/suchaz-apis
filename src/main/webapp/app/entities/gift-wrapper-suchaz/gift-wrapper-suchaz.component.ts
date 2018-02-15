import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { GiftWrapperSuchaz } from './gift-wrapper-suchaz.model';
import { GiftWrapperSuchazService } from './gift-wrapper-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-gift-wrapper-suchaz',
    templateUrl: './gift-wrapper-suchaz.component.html'
})
export class GiftWrapperSuchazComponent implements OnInit, OnDestroy {
giftWrappers: GiftWrapperSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private giftWrapperService: GiftWrapperSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.giftWrapperService.query().subscribe(
            (res: HttpResponse<GiftWrapperSuchaz[]>) => {
                this.giftWrappers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInGiftWrappers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: GiftWrapperSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInGiftWrappers() {
        this.eventSubscriber = this.eventManager.subscribe('giftWrapperListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
