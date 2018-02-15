import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemCommonAttributeSuchaz } from './item-common-attribute-suchaz.model';
import { ItemCommonAttributeSuchazService } from './item-common-attribute-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-item-common-attribute-suchaz',
    templateUrl: './item-common-attribute-suchaz.component.html'
})
export class ItemCommonAttributeSuchazComponent implements OnInit, OnDestroy {
itemCommonAttributes: ItemCommonAttributeSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemCommonAttributeService: ItemCommonAttributeSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.itemCommonAttributeService.query().subscribe(
            (res: HttpResponse<ItemCommonAttributeSuchaz[]>) => {
                this.itemCommonAttributes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInItemCommonAttributes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ItemCommonAttributeSuchaz) {
        return item.id;
    }
    registerChangeInItemCommonAttributes() {
        this.eventSubscriber = this.eventManager.subscribe('itemCommonAttributeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
