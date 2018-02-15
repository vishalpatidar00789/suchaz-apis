import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ItemSuchaz } from './item-suchaz.model';
import { ItemSuchazService } from './item-suchaz.service';

@Component({
    selector: 'jhi-item-suchaz-detail',
    templateUrl: './item-suchaz-detail.component.html'
})
export class ItemSuchazDetailComponent implements OnInit, OnDestroy {

    item: ItemSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private itemService: ItemSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInItems();
    }

    load(id) {
        this.itemService.find(id)
            .subscribe((itemResponse: HttpResponse<ItemSuchaz>) => {
                this.item = itemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInItems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'itemListModification',
            (response) => this.load(this.item.id)
        );
    }
}
