/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { WishListSuchazDialogComponent } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz-dialog.component';
import { WishListSuchazService } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz.service';
import { WishListSuchaz } from '../../../../../../main/webapp/app/entities/wish-list-suchaz/wish-list-suchaz.model';
import { SuchAzUserSuchazService } from '../../../../../../main/webapp/app/entities/such-az-user-suchaz';

describe('Component Tests', () => {

    describe('WishListSuchaz Management Dialog Component', () => {
        let comp: WishListSuchazDialogComponent;
        let fixture: ComponentFixture<WishListSuchazDialogComponent>;
        let service: WishListSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [WishListSuchazDialogComponent],
                providers: [
                    SuchAzUserSuchazService,
                    WishListSuchazService
                ]
            })
            .overrideTemplate(WishListSuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListSuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WishListSuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.wishList = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'wishListListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new WishListSuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.wishList = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'wishListListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
