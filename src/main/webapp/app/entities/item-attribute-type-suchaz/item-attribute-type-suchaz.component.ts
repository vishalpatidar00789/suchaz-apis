import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemAttributeTypeSuchaz } from './item-attribute-type-suchaz.model';
import { ItemAttributeTypeSuchazService } from './item-attribute-type-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-item-attribute-type-suchaz',
    templateUrl: './item-attribute-type-suchaz.component.html'
})
export class ItemAttributeTypeSuchazComponent implements OnInit, OnDestroy {
itemAttributeTypes: ItemAttributeTypeSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemAttributeTypeService: ItemAttributeTypeSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.itemAttributeTypeService.query().subscribe(
            (res: HttpResponse<ItemAttributeTypeSuchaz[]>) => {
                this.itemAttributeTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInItemAttributeTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ItemAttributeTypeSuchaz) {
        return item.id;
    }
    registerChangeInItemAttributeTypes() {
        this.eventSubscriber = this.eventManager.subscribe('itemAttributeTypeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
