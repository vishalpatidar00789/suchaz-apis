/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { CountrySuchazDialogComponent } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz-dialog.component';
import { CountrySuchazService } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz.service';
import { CountrySuchaz } from '../../../../../../main/webapp/app/entities/country-suchaz/country-suchaz.model';

describe('Component Tests', () => {

    describe('CountrySuchaz Management Dialog Component', () => {
        let comp: CountrySuchazDialogComponent;
        let fixture: ComponentFixture<CountrySuchazDialogComponent>;
        let service: CountrySuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CountrySuchazDialogComponent],
                providers: [
                    CountrySuchazService
                ]
            })
            .overrideTemplate(CountrySuchazDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CountrySuchazDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CountrySuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CountrySuchaz(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.country = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'countryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new CountrySuchaz();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.country = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'countryListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
